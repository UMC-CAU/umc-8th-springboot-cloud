// src/main/java/umc/spring/repository/missiontemplate/MissionTemplateRepository.java
package umc.spring.repository.missiontemplate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.Store;

public interface MissionTemplateRepository extends JpaRepository<MissionTemplate, Long> {

    /* 특정 가게 미션 목록 + 페이징 */
    Page<MissionTemplate> findAllByStore(Store store, Pageable pageable);
}
