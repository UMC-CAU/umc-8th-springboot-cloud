package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.MissionTemplate;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

@Entity @Getter
@Builder @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15, columnDefinition = "VARCHAR(15) DEFAULT 'ASSIGNED'")
    private MissionStatus status = MissionStatus.ASSIGNED;

    @Column(nullable = false)
    private LocalDateTime assignedAt;

    private LocalDateTime completedAt;
    private LocalDateTime reviewedAt;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "mission_template_id")
    private MissionTemplate missionTemplate;

    public void setStatus(MissionStatus status) { this.status = status; }
    public void setCompletedAt(LocalDateTime t) { this.completedAt = t; }
}
