package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.StoreCommandService;
import umc.spring.web.dto.mission.MissionDto;

@RestController
@RequestMapping("/regions") @RequiredArgsConstructor
public class StoreController {

    private final StoreCommandService storeSvc;

    @PostMapping("/{regionId}/stores")
    public ApiResponse<MissionDto.StoreCreateRes> createStore(
            @PathVariable Long regionId,
            @RequestBody @Valid MissionDto.StoreCreateReq req) {
        return ApiResponse.onSuccess(storeSvc.addStore(regionId, req));
    }
}
