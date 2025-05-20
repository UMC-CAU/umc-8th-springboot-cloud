package umc.spring.service;

import umc.spring.web.dto.mission.MissionDto;

public interface StoreCommandService {
    MissionDto.StoreCreateRes addStore(Long regionId, MissionDto.StoreCreateReq req);
}