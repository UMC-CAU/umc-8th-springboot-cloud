package umc.spring.service;

import umc.spring.web.dto.mission.MissionDto;

public interface ReviewCommandService {
    MissionDto.ReviewCreateRes writeReview(Long storeId, MissionDto.ReviewCreateReq req);
}