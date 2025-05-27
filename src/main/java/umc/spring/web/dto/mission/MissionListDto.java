// src/main/java/umc/spring/web/dto/mission/MissionListDto.java
package umc.spring.web.dto.mission;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionListDto {

    /* ---------- 2. 특정 가게의 미션 목록 ---------- */
    @Getter @Builder @AllArgsConstructor
    public static class StoreMissionPreview {
        private Long missionTemplateId;
        private String condition;
        private Integer reward;
    }

    @Getter @Builder @AllArgsConstructor
    public static class StoreMissionPage {
        private List<StoreMissionPreview> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    /* ---------- 3. 내 진행중인 미션 목록 ---------- */
    @Getter @Builder @AllArgsConstructor
    public static class MyMissionPreview {
        private Long userMissionId;
        private String status;
        private LocalDateTime assignedAt;
        private String condition;
        private Integer reward;
        private String storeName;
        private String regionName;
    }

    @Getter @Builder @AllArgsConstructor
    public static class MyMissionPage {
        private List<MyMissionPreview> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
