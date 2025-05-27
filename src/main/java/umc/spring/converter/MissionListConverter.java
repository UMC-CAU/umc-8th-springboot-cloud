// src/main/java/umc/spring/converter/MissionListConverter.java
package umc.spring.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.mission.MissionListDto;

public class MissionListConverter {

    /* ---------- 가게 미션 ---------- */
    private static MissionListDto.StoreMissionPreview toStorePreview(MissionTemplate mt) {
        return MissionListDto.StoreMissionPreview.builder()
                .missionTemplateId(mt.getId())
                .condition(mt.getCondition())
                .reward(Integer.parseInt(mt.getReward()))
                .build();
    }

    public static MissionListDto.StoreMissionPage toStorePage(Page<MissionTemplate> page) {
        List<MissionListDto.StoreMissionPreview> list = page.stream()
                .map(MissionListConverter::toStorePreview)
                .collect(Collectors.toList());

        return MissionListDto.StoreMissionPage.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    /* ---------- 내 진행중인 미션 ---------- */
    private static MissionListDto.MyMissionPreview toMyPreview(UserMission um) {
        var mt = um.getMissionTemplate();
        var store = mt.getStore();
        return MissionListDto.MyMissionPreview.builder()
                .userMissionId(um.getId())
                .status(um.getStatus().name())
                .assignedAt(um.getAssignedAt())
                .condition(mt.getCondition())
                .reward(Integer.parseInt(mt.getReward()))
                .storeName(store.getName())
                .regionName(store.getRegion().getName())
                .build();
    }

    public static MissionListDto.MyMissionPage toMyPage(Page<UserMission> page) {
        List<MissionListDto.MyMissionPreview> list = page.stream()
                .map(MissionListConverter::toMyPreview)
                .collect(Collectors.toList());

        return MissionListDto.MyMissionPage.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
