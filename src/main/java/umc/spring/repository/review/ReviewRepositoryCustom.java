package umc.spring.repository.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.web.dto.MyReviewDto;

/**
 * - 내 리뷰 페이징 조회
 * - 직접 INSERT 후 PK 반환
 */
public interface ReviewRepositoryCustom {

    Page<MyReviewDto> findMyReviews(Long userId, Pageable pageable);

    /**
     * @return 새로 입력된 Review PK
     */
    Long insertReview(Long userId, Long storeId, String content, int rating);
}
