package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.TermsType;
import umc.spring.domain.mapping.UserTerms;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TermsType type;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob @Column(nullable = false)
    private String content;

    /* 양방향 */
    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTerms> userTermsList = new ArrayList<>();
}