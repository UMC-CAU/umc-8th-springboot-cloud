package umc.spring.repository.user;

import umc.spring.web.dto.UserProfileDto;

public interface UserRepositoryCustom {
    UserProfileDto fetchProfile(Long userId);
}