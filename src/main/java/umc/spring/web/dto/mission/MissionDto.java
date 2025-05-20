package umc.spring.web.dto.mission;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

public class MissionDto {

    /* ---------- 1. 매장 생성 ---------- */
    @Getter @Setter
    public static class StoreCreateReq {
        @NotBlank private String name;
        @NotBlank private String address;
    }
    @Builder @Getter
    public static class StoreCreateRes { private Long storeId; }

    /* ---------- 2. 리뷰 ---------- */
    @Getter @Setter
    public static class ReviewCreateReq {
        @PositiveOrZero @Max(5) private double rating;
        @NotBlank private String content;
        private List<@NotBlank String> imageUrls;
    }
    @Builder @Getter
    public static class ReviewCreateRes { private Long reviewId; }

    /* ---------- 3. 미션 템플릿 ---------- */
    @Getter @Setter
    public static class MissionTemplateCreateReq {
        @NotBlank private String condition;
        @Positive @Max(10_000) private int reward;
    }
    @Builder @Getter
    public static class MissionTemplateCreateRes { private Long missionTemplateId; }

    /* ---------- 4. 미션 도전 ---------- */
    @Builder @Getter
    public static class MissionChallengeRes { private Long userMissionId; }
}
