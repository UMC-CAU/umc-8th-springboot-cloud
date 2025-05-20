package umc.spring.service;

import umc.spring.web.dto.mission.MissionDto;

public interface MissionTemplateCommandService {
    MissionDto.MissionTemplateCreateRes addMissionTemplate(Long storeId,
                                                           MissionDto.MissionTemplateCreateReq req);
}