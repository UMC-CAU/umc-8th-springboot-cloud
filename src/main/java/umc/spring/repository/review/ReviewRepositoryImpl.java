// ReviewRepositoryImpl.java
package umc.spring.repository.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAInsertClause;
import com.querydsl.core.types.Projections;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.web.dto.MyReviewDto;
import umc.spring.domain.QReview;
import umc.spring.domain.QStore;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory query;
    private final QReview r = QReview.review;
    private final QStore s = QStore.store;

    @Override
    public Page<MyReviewDto> findMyReviews(Long userId, Pageable pageable) {

        List<MyReviewDto> content = query
                .select(Projections.constructor(MyReviewDto.class,
                        r.id,
                        r.content,
                        r.rating,
                        r.createdAt,
                        s.name))
                .from(r)
                .join(r.store, s)
                .where(r.user.id.eq(userId))
                .orderBy(r.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(r.count())
                .from(r)
                .where(r.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    @Transactional
    public Long insertReview(Long userId, Long storeId, String content, int rating) {

        JPAInsertClause insert = query.insert(r)
                .columns(r.content, r.rating, r.createdAt, r.store.id, r.user.id)
                .values(content, rating, LocalDateTime.now(), storeId, userId);

        return insert.execute();
    }
}