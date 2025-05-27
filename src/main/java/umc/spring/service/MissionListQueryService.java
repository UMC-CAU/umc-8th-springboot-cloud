// src/main/java/umc/spring/service/MissionListQueryService.java
package umc.spring.service;

import org.springframework.data.domain.Page;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.mapping.UserMission;

public interface MissionListQueryService {
    Page<MissionTemplate> getStoreMissions(Long storeId, int page);
    Page<UserMission>    getMyMissions(Long userId, int page);
}
