package umc.spring.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import umc.spring.domain.*;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.mission.MissionDto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionConverter {

    /* ---------- Store ---------- */
    public static Store toStore(MissionDto.StoreCreateReq r) {
        return Store.builder()
                .name(r.getName())
                .address(r.getAddress())
                .score(0f)
                .missionTemplates(new java.util.ArrayList<>())
                .reviews(new java.util.ArrayList<>())
                .build();
    }
    public static MissionDto.StoreCreateRes toStoreRes(Store s) {
        return MissionDto.StoreCreateRes.builder().storeId(s.getId()).build();
    }

    /* ---------- MissionTemplate ---------- */
    public static MissionTemplate toMissionTemplate(MissionDto.MissionTemplateCreateReq r, Store store) {
        return MissionTemplate.builder()
                .condition(r.getCondition())
                .reward(String.valueOf(r.getReward()))
                .store(store)
                .build();
    }
    public static MissionDto.MissionTemplateCreateRes toMissionTemplateRes(MissionTemplate mt) {
        return MissionDto.MissionTemplateCreateRes.builder()
                .missionTemplateId(mt.getId()).build();
    }

    /* ---------- Review ---------- */
    public static Review toReview(MissionDto.ReviewCreateReq r, Store store, User user) {
        Review review = Review.builder()
                .rating((int) (r.getRating() * 10))   // 0.5 단위 ⇒ 5점 만점 *10 저장 예
                .content(r.getContent())
                .store(store)
                .user(user)
                .images(new java.util.ArrayList<>())
                .build();

        if (r.getImageUrls() != null) {
            review.getImages().addAll(
                    r.getImageUrls().stream()
                            .map(url -> ReviewImage.builder()
                                    .imageUrl(url)
                                    .review(review)
                                    .build())
                            .collect(Collectors.toList()));
        }
        return review;
    }
    public static MissionDto.ReviewCreateRes toReviewRes(Review r) {
        return MissionDto.ReviewCreateRes.builder().reviewId(r.getId()).build();
    }

    /* ---------- UserMission (Challenge) ---------- */
    public static MissionDto.MissionChallengeRes toChallengeRes(UserMission um) {
        return MissionDto.MissionChallengeRes.builder().userMissionId(um.getId()).build();
    }
}
