package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.ReviewCommandService;
import umc.spring.validation.annotation.ExistingStore;
import umc.spring.web.dto.mission.MissionDto;

@RestController
@RequestMapping("/stores") @RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewSvc;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<MissionDto.ReviewCreateRes> writeReview(
            @PathVariable @ExistingStore Long storeId,
            @RequestBody @Valid MissionDto.ReviewCreateReq req) {
        return ApiResponse.onSuccess(reviewSvc.writeReview(storeId, req));
    }
}
