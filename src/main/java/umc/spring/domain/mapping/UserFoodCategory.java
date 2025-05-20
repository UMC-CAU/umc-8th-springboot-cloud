package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;

@Entity @Getter
@Builder @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserFoodCategory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;
}