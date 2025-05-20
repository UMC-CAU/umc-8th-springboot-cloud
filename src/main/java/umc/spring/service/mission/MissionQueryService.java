// src/main/java/umc/spring/service/mission/MissionQueryService.java
package umc.spring.service.mission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.usermission.UserMissionRepositoryCustom;
import umc.spring.web.dto.UserMissionSummaryDto;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MissionQueryService {

    private final UserMissionRepositoryCustom userMissionRepo;

    // 여기에 @Qualifier를 붙여 두 개 중 "userMissionRepository" 한 개만 받도록 지정
    public MissionQueryService(
            @Qualifier("userMissionRepository") UserMissionRepositoryCustom userMissionRepo) {
        this.userMissionRepo = userMissionRepo;
    }

    public Page<UserMissionSummaryDto> getMyMissions(Long userId, Pageable pageable) {
        return userMissionRepo.findMyMissions(
                userId,
                List.of(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED, MissionStatus.REVIEWED),
                pageable
        );
    }
}
