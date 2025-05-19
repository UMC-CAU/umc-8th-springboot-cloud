package umc.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.web.dto.UserMissionSummaryDto;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.usermission.UserMissionRepositoryCustom;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final UserMissionRepositoryCustom userMissionRepo;

    public Page<UserMissionSummaryDto> getMyMissions(Long userId,
                                                     Pageable pageable) {

        return userMissionRepo.findMyMissions(
                userId,
                List.of(MissionStatus.IN_PROGRESS,
                        MissionStatus.COMPLETED,
                        MissionStatus.REVIEWED),
                pageable);
    }
}