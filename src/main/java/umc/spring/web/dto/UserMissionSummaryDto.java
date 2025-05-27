package umc.spring.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor           // QueryDSL-Projections용
@NoArgsConstructor            // JPA(리플렉션) 대비
public class UserMissionSummaryDto {

    private Long userMissionId;
    private String status;
    private LocalDateTime assignedAt;
    private LocalDateTime completedAt;
    private String missionCondition;
    private String missionReward;   // ★ Integer → String 으로 수정
    private String storeName;
    private String regionName;

    @Override
    public String toString() {
        return "UserMissionSummaryDto{" +
                "userMissionId=" + userMissionId +
                ", status='" + status + '\'' +
                ", assignedAt=" + assignedAt +
                ", completedAt=" + completedAt +
                ", missionCondition='" + missionCondition + '\'' +
                ", missionReward='" + missionReward + '\'' +
                ", storeName='" + storeName + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}