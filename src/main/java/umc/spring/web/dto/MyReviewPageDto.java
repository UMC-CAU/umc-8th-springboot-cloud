// src/main/java/umc/spring/web/dto/MyReviewPageDto.java
package umc.spring.web.dto;

import java.util.List;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyReviewPageDto {

    private List<MyReviewDto> reviewList; // 목록
    private Integer listSize;             // 현재 페이지 실제 목록 크기
    private Integer totalPage;            // 전체 페이지 수
    private Long totalElements;           // 전체 리뷰 수
    private Boolean isFirst;              // 첫 페이지 여부
    private Boolean isLast;               // 마지막 페이지 여부
}
