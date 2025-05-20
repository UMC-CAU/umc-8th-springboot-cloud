package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Lob
    private String address;

    @Column(nullable = true)
    private Float score;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    /* 양방향 */
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionTemplate> missionTemplates = new ArrayList<>();

    /* ────────────── ⭐ 추가 부분 ⭐ ────────────── */
    /**
     * Region-Store 연관관계 편의 메서드
     * (단방향이면 region 컬렉션 추가 부분은 지워도 됩니다)
     */
    public void changeRegion(Region region) {
        // 의도치 않은 중복 추가 방지
        if (this.region != null) {
            this.region.getStores().remove(this);
        }
        this.region = region;
        region.getStores().add(this);   // ← Region 엔티티에 stores 컬렉션이 있을 때만
    }
    /* ─────────────────────────────────────────── */

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", region=" + (region != null ? region.getName() : "N/A") +
                '}';
    }
}
