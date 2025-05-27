// src/main/java/umc/spring/web/controller/MissionProgressController.java
package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.UserMissionCommandService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MissionProgressController {

    private final UserMissionCommandService cmdSvc;

    @PatchMapping("/{userId}/missions/{userMissionId}")
    @Operation(summary = "진행중인 미션 완료 처리")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "완료")
    })
    public ApiResponse<Void> completeMission(
            @PathVariable("userId") Long userId,
            @PathVariable("userMissionId") Long userMissionId){

        cmdSvc.completeMission(userId, userMissionId);
        return ApiResponse.onSuccess(null);
    }
}
