// src/main/java/umc/spring/web/controller/MyMissionController.java
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class MyMissionController {

    private final MissionListQueryService querySvc;

    @GetMapping("/{userId}/missions")
    @Operation(summary = "내 진행중인 미션 목록")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 PK"),
            @Parameter(name = "page",   description = "페이지 번호(1부터)")
    })
    public ApiResponse<MissionListDto.MyMissionPage> listMyMissions(
            @PathVariable("userId") Long userId,
            @PageValid Integer page){

        return ApiResponse.onSuccess(
                MissionListConverter.toMyPage(
                        querySvc.getMyMissions(userId, page)));
    }
}
