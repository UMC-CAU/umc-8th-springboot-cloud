package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvailableMissionDto {
    private Long missionTemplateId;
    private String missionCondition;
    private Integer missionReward;
    private String storeName;
}