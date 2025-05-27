// src/main/java/umc/spring/web/controller/StoreMissionController.java
package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionListConverter;
import umc.spring.service.MissionListQueryService;
import umc.spring.validation.annotation.PageValid;
import umc.spring.web.dto.mission.MissionListDto;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreMissionController {

    private final MissionListQueryService querySvc;

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록")
    @Parameters({
            @Parameter(name = "storeId", description = "가게 PK"),
            @Parameter(name = "page",    description = "페이지 번호(1부터)")
    })
    public ApiResponse<MissionListDto.StoreMissionPage> listStoreMissions(
            @PathVariable("storeId") Long storeId,
            @PageValid Integer page) {

        return ApiResponse.onSuccess(
                MissionListConverter.toStorePage(
                        querySvc.getStoreMissions(storeId, page)));
    }
}
