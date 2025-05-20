package umc.spring.service;

import umc.spring.web.dto.mission.MissionDto;

public interface MissionChallengeService {
    MissionDto.MissionChallengeRes challenge(Long missionId);
}