package umc.spring.repository.missiontemplate;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.web.dto.AvailableMissionDto;
import umc.spring.domain.QMissionTemplate;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.mapping.QUserMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionTemplateRepositoryImpl implements MissionTemplateRepositoryCustom {

    private final JPAQueryFactory query;
    private final QMissionTemplate mt = QMissionTemplate.missionTemplate;
    private final QStore s = QStore.store;
    private final QRegion r = QRegion.region;
    private final QUserMission um = QUserMission.userMission;

    @Override
    public Page<AvailableMissionDto> findAvailableMissions(Long regionId,
                                                           Long userId,
                                                           Pageable pageable) {

        List<AvailableMissionDto> content = query
                .select(Projections.constructor(AvailableMissionDto.class,
                        mt.id,
                        mt.condition,
                        mt.reward,
                        s.name))
                .from(mt)
                .join(mt.store, s)
                .join(s.region, r)
                .leftJoin(um)
                .on(um.missionTemplate.id.eq(mt.id)
                        .and(um.user.id.eq(userId)))
                .where(r.id.eq(regionId),
                        um.id.isNull())          // 아직 도전 안 한 미션
                .orderBy(mt.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(mt.count())
                .from(mt)
                .join(mt.store, s)
                .join(s.region, r)
                .leftJoin(um)
                .on(um.missionTemplate.id.eq(mt.id)
                        .and(um.user.id.eq(userId)))
                .where(r.id.eq(regionId),
                        um.id.isNull())
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}