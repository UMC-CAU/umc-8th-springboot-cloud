package umc.spring.repository.usermission;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.*;

import java.util.List;

public interface UserMissionRepository
        extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {

    /** 이미 ASSIGNED/InProgress 상태인지 중복 체크 */
    boolean existsByUserAndMissionTemplateAndStatusIn(
            User user,
            MissionTemplate missionTemplate,
            List<MissionStatus> statuses);
}
