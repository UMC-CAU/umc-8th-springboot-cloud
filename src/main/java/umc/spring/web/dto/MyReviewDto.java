package umc.spring.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyReviewDto {
    private Long reviewId;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
    private String storeName;
}