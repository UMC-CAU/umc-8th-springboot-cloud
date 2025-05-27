// src/main/java/umc/spring/web/controller/MyReviewController.java
package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.service.ReviewQueryService;
import umc.spring.validation.annotation.PageValid;
import umc.spring.web.dto.MyReviewPageDto;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MyReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{userId}/reviews")
    @Operation(
            summary = "내가 작성한 리뷰 목록",
            description = "특정 사용자가 작성한 리뷰를 10개 단위로 페이징 조회한다.")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 PK"),
            @Parameter(name = "page",   description = "페이지 번호(1부터 시작)")
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "성공")
    })
    public ApiResponse<MyReviewPageDto> getMyReviews(
            @PathVariable("userId") Long userId, // ← 이름 명시(필수)
            @PageValid Integer page) {

        return ApiResponse.onSuccess(
                ReviewConverter.toMyReviewPageDto(
                        reviewQueryService.getMyReviewList(userId, page)
                )
        );
    }
}
