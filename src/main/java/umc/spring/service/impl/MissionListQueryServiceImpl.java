// src/main/java/umc/spring/service/impl/MissionListQueryServiceImpl.java
package umc.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.missiontemplate.MissionTemplateRepository;
import umc.spring.repository.store.StoreRepository;
import umc.spring.repository.usermission.UserMissionRepository;
import umc.spring.service.MissionListQueryService;

@Service
@RequiredArgsConstructor
public class MissionListQueryServiceImpl implements MissionListQueryService {

    private final MissionTemplateRepository mtRepo;
    private final StoreRepository storeRepo;
    private final UserMissionRepository umRepo;

    private static final int PAGE_SIZE = 10;

    @Override
    public Page<MissionTemplate> getStoreMissions(Long storeId, int page) {
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("store X"));
        return mtRepo.findAllByStore(
                store,
                PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    @Override
    public Page<UserMission> getMyMissions(Long userId, int page) {
        return umRepo.findAllByUserIdAndStatus(
                userId,
                MissionStatus.IN_PROGRESS,
                PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "assignedAt")));
    }
}
