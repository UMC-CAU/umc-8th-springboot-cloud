package umc.spring.repository.store;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QStore;
import umc.spring.domain.Store;

import java.util.List;

/**
 * 커스텀 레포지토리 구현부 – 반드시 'Impl' 로 끝나야
 * 스프링이 자동으로 연결(JPA 리포지토리 확장 규칙).
 */
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {

        QStore s = QStore.store;
        BooleanBuilder builder = new BooleanBuilder();

        if (name != null && !name.isBlank()) {
            builder.and(s.name.eq(name));
        }
        if (score != null) {
            builder.and(s.score.goe(score));
        }

        return new JPAQueryFactory(em)
                .selectFrom(s)
                .where(builder)
                .fetch();
    }
}
