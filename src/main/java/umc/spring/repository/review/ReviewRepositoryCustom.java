package umc.spring.repository.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.web.dto.MyReviewDto;

public interface ReviewRepositoryCustom {
    Page<MyReviewDto> findMyReviews(Long userId, Pageable pageable);
    Long insertReview(Long userId, Long storeId, String content, int rating);
}