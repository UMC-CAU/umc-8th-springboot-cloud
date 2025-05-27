// src/main/java/umc/spring/repository/usermission/UserMissionRepository.java
package umc.spring.repository.usermission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.User;
import umc.spring.domain.MissionTemplate;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository
        extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {

    /* 이미 ASSIGNED/IN_PROGRESS 상태인지 중복 체크 */
    boolean existsByUserAndMissionTemplateAndStatusIn(
            User user, MissionTemplate missionTemplate, List<MissionStatus> statuses);

    /* 내 진행중인(또는 특정 status) 미션 목록 + 페이징 */
    Page<UserMission> findAllByUserIdAndStatus(Long userId, MissionStatus status, Pageable pageable);

    /* userId 소유 검증용 단건 조회 */
    Optional<UserMission> findByIdAndUserId(Long id, Long userId);
}
