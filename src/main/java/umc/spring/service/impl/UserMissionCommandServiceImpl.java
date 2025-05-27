// src/main/java/umc/spring/service/impl/UserMissionCommandServiceImpl.java
package umc.spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.usermission.UserMissionRepository;
import umc.spring.service.UserMissionCommandService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserMissionRepository umRepo;

    @Override
    public void completeMission(Long userId, Long userMissionId) {
        UserMission um = umRepo.findByIdAndUserId(userMissionId, userId)
                .orElseThrow(() -> new IllegalArgumentException("userMission X"));

        if (um.getStatus() == MissionStatus.COMPLETED)
            throw new IllegalStateException("이미 완료된 미션");

        if (um.getStatus() == MissionStatus.REVIEWED)
            throw new IllegalStateException("리뷰 완료 상태");

        um.setStatus(MissionStatus.COMPLETED);
        um.setCompletedAt(java.time.LocalDateTime.now());
    }
}
