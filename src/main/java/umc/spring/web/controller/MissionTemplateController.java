package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MissionTemplateCommandService;
import umc.spring.validation.annotation.ExistingStore;
import umc.spring.web.dto.mission.MissionDto;

@RestController
@RequestMapping("/stores") @RequiredArgsConstructor
public class MissionTemplateController {

    private final MissionTemplateCommandService mtSvc;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionDto.MissionTemplateCreateRes> addMission(
            @PathVariable @ExistingStore Long storeId,
            @RequestBody @Valid MissionDto.MissionTemplateCreateReq req) {
        return ApiResponse.onSuccess(mtSvc.addMissionTemplate(storeId, req));
    }
}
