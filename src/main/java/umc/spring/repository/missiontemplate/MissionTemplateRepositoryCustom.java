package umc.spring.repository.missiontemplate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.web.dto.AvailableMissionDto;

public interface MissionTemplateRepositoryCustom {
    Page<AvailableMissionDto> findAvailableMissions(Long regionId,
                                                    Long userId,
                                                    Pageable pageable);
}