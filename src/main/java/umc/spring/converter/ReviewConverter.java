// src/main/java/umc/spring/converter/ReviewConverter.java
package umc.spring.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.spring.web.dto.MyReviewDto;
import umc.spring.web.dto.MyReviewPageDto;

public class ReviewConverter {

    /** Page<MyReviewDto> ➜ MyReviewPageDto (for 문 금지, Stream 사용) */
    public static MyReviewPageDto toMyReviewPageDto(Page<MyReviewDto> page){
        List<MyReviewDto> content = page.getContent();

        return MyReviewPageDto.builder()
                .reviewList(content)
                .listSize(content.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
