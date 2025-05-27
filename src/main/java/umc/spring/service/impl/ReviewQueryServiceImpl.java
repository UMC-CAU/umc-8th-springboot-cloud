// src/main/java/umc/spring/service/impl/ReviewQueryServiceImpl.java
package umc.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.review.ReviewRepository;
import umc.spring.service.ReviewQueryService;
import umc.spring.web.dto.MyReviewDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<MyReviewDto> getMyReviewList(Long userId, int page) {
        // 10건 고정, 최신순(createdAt desc) → 이미 커스텀 쿼리에서 정렬
        return reviewRepository.findMyReviews(userId, PageRequest.of(page, 10));
    }
}
