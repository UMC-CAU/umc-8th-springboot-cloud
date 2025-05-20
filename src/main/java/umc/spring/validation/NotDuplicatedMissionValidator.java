// validation/NotDuplicatedMissionValidator.java
package umc.spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.missiontemplate.MissionTemplateRepository;
import umc.spring.repository.user.UserRepository;
import umc.spring.repository.usermission.UserMissionRepository;
import umc.spring.validation.annotation.NotDuplicatedMission;

import java.util.List;

/**
 * 이미 ASSIGNED·IN_PROGRESS 상태인 동일 미션이 존재하는지 검증하는 Validator.
 */
@Component                                // ★ 반드시 Bean 등록
@RequiredArgsConstructor
public class NotDuplicatedMissionValidator
        implements ConstraintValidator<NotDuplicatedMission, Long> {

    private final UserMissionRepository userMissionRepository;
    private final MissionTemplateRepository missionTemplateRepository;
    private final UserRepository userRepository;

    private static final Long HARD_USER_ID = 1L;   // 로그인 기능 없으므로 고정 유저 사용

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext ctx) {

        /* 1) missionId 자체가 null ⇒ 다른 제약에서 걸리도록 true 반환 */
        if (missionId == null) return true;

        /* 2) 미션 존재 여부 검사 (없으면 검증 실패) */
        MissionTemplate mt = missionTemplateRepository.findById(missionId).orElse(null);
        if (mt == null) return false;

        /* 3) 하드코딩 유저 로드 (없으면 검증 실패) */
        User user = userRepository.findById(HARD_USER_ID).orElse(null);
        if (user == null) return false;

        /* 4) 이미 도전 중인지 확인 */
        boolean duplicated = userMissionRepository.existsByUserAndMissionTemplateAndStatusIn(
                user,
                mt,
                List.of(MissionStatus.ASSIGNED, MissionStatus.IN_PROGRESS)
        );

        return !duplicated;
    }
}
