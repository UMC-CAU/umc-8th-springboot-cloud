// repository/missiontemplate/MissionTemplateRepository.java
package umc.spring.repository.missiontemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.MissionTemplate;

public interface MissionTemplateRepository
        extends JpaRepository<MissionTemplate, Long> { }
