package umc.spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.store.StoreRepository;
import umc.spring.repository.review.ReviewRepository;
import umc.spring.repository.user.UserRepository;
import umc.spring.service.ReviewCommandService;
import umc.spring.web.dto.mission.MissionDto;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private static final Long HARD_USER_ID = 1L;

    public MissionDto.ReviewCreateRes writeReview(Long storeId, MissionDto.ReviewCreateReq req) {
        Store store = null;
        try {
            store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        User user = null;
        try {
            user = userRepository.findById(HARD_USER_ID)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        var review = MissionConverter.toReview(req, store, user);
        return MissionConverter.toReviewRes(reviewRepository.save(review));
    }
}
