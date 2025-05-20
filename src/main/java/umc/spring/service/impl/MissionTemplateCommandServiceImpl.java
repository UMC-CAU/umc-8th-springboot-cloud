package umc.spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Store;
import umc.spring.repository.store.StoreRepository;
import umc.spring.repository.missiontemplate.MissionTemplateRepository;
import umc.spring.service.MissionTemplateCommandService;
import umc.spring.web.dto.mission.MissionDto;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionTemplateCommandServiceImpl implements MissionTemplateCommandService {

    private final StoreRepository storeRepository;
    private final MissionTemplateRepository missionTemplateRepository;

    public MissionDto.MissionTemplateCreateRes addMissionTemplate(Long storeId,
                                                                  MissionDto.MissionTemplateCreateReq req) {
        Store store = null;
        try {
            store = storeRepository.findById(storeId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        var mt = MissionConverter.toMissionTemplate(req, store);
        return MissionConverter.toMissionTemplateRes(missionTemplateRepository.save(mt));
    }
}
