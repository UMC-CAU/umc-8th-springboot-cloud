package umc.spring.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Projections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.web.dto.UserProfileDto;
import umc.spring.domain.QUser;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;
    private final QUser u = QUser.user;

    @Override
    public UserProfileDto fetchProfile(Long userId) {
        return query
                .select(Projections.constructor(UserProfileDto.class,
                        u.id,
                        u.email,
                        u.nickname,
                        u.name,
                        u.gender.stringValue(),
                        u.phoneNumber,
                        u.status.stringValue(),
                        u.inactiveDate,
                        u.address,
                        u.createdAt))
                .from(u)
                .where(u.id.eq(userId))
                .fetchOne();
    }
}