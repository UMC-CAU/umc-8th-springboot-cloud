// src/main/java/umc/spring/service/ReviewQueryService.java
package umc.spring.service;

import org.springframework.data.domain.Page;
import umc.spring.web.dto.MyReviewDto;

public interface ReviewQueryService {

    /** userId 기준 “내 리뷰” 페이징 조회 (page 인덱스는 0-base) */
    Page<MyReviewDto> getMyReviewList(Long userId, int page);
}
