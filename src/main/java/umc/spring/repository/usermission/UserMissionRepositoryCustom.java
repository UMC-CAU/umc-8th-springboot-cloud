package umc.spring.repository.usermission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.web.dto.UserMissionSummaryDto;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface UserMissionRepositoryCustom {
    Page<UserMissionSummaryDto> findMyMissions(Long userId,
                                               List<MissionStatus> statuses,
                                               Pageable pageable);
}