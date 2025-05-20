package umc.spring.repository.review;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAInsertClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QReview;
import umc.spring.domain.QStore;
import umc.spring.web.dto.MyReviewDto;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory query;

    private static final QReview r = QReview.review;
    private static final QStore  s = QStore.store;

    /* --------------------------------------------------
     * 내 리뷰 목록 조회 (Store 필터 X, 최신순)
     * -------------------------------------------------- */
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

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    /* --------------------------------------------------
     * INSERT (QueryDSL JPAInsertClause 사용)
     * -------------------------------------------------- */
    @Override
    @Transactional
    public Long insertReview(Long userId, Long storeId, String content, int rating) {

        // INSERT ... VALUES (...)
        JPAInsertClause insert = query.insert(r)
                .columns(r.content,
                        r.rating,
                        r.createdAt,
                        r.store.id,
                        r.user.id)
                .values(content,
                        rating,
                        LocalDateTime.now(),
                        storeId,
                        userId);

        // execute() → 영향 받은 row 수 / executeWithKey() → PK
        return insert.execute();
    }
}
