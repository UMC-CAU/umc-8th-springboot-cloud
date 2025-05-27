package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MissionChallengeService;
import umc.spring.validation.annotation.NotDuplicatedMission;
import umc.spring.web.dto.mission.MissionDto;

@RestController
@RequestMapping("/missions") @RequiredArgsConstructor
public class MissionChallengeController {

    private final MissionChallengeService challengeSvc;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionDto.MissionChallengeRes> challenge(
            @PathVariable @NotDuplicatedMission Long missionId) {
        return ApiResponse.onSuccess(challengeSvc.challenge(missionId));
    }
}
