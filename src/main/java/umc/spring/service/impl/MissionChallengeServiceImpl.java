package umc.spring.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.exception.DuplicateException;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.missiontemplate.MissionTemplateRepository;
import umc.spring.repository.user.UserRepository;
import umc.spring.repository.usermission.UserMissionRepository;
import umc.spring.service.MissionChallengeService;
import umc.spring.web.dto.mission.MissionDto;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionChallengeServiceImpl implements MissionChallengeService {

    private final MissionTemplateRepository missionTemplateRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private static final Long HARD_USER_ID = 1L;

    public MissionDto.MissionChallengeRes challenge(Long missionId) {
        MissionTemplate mt = null;
        try {
            mt = missionTemplateRepository.findById(missionId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        User user = null;
        try {
            user = userRepository.findById(HARD_USER_ID)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        if (userMissionRepository.existsByUserAndMissionTemplateAndStatusIn(
                user, mt,
                java.util.List.of(MissionStatus.ASSIGNED, MissionStatus.IN_PROGRESS)))
            throw new DuplicateException("이미 도전 중인 미션입니다.");

        var um = UserMission.builder()
                .user(user)
                .missionTemplate(mt)
                .status(MissionStatus.ASSIGNED)
                .assignedAt(java.time.LocalDateTime.now())
                .build();

        return MissionConverter.toChallengeRes(userMissionRepository.save(um));
    }
}
