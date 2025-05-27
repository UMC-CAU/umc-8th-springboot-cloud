// src/main/java/umc/spring/service/UserMissionCommandService.java
package umc.spring.service;

public interface UserMissionCommandService {
    void completeMission(Long userId, Long userMissionId);
}
