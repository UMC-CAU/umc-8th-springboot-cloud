package umc.spring.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.region.RegionRepository;
import umc.spring.repository.store.StoreRepository;   // ✅ 패키지 경로 정리
import umc.spring.service.StoreCommandService;
import umc.spring.web.dto.mission.MissionDto.StoreCreateReq;
import umc.spring.web.dto.mission.MissionDto.StoreCreateRes;


@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final RegionRepository regionRepository;
    private final StoreRepository  storeRepository;

    @Override
    public StoreCreateRes addStore(Long regionId, StoreCreateReq req) {

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new EntityNotFoundException("Region id=" + regionId));

        // DTO → Entity
        Store store = MissionConverter.toStore(req);   // 이름·주소·score 등만 채워져 있음
        store.changeRegion(region);                    // 연관관계 편의 메서드로 세팅

        return MissionConverter.toStoreRes(storeRepository.save(store));
    }
}
