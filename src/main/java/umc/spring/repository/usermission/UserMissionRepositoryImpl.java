// UserMissionRepositoryImpl.java
package umc.spring.repository.usermission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Projections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.web.dto.UserMissionSummaryDto;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QUserMission;
import umc.spring.domain.QMissionTemplate;
import umc.spring.domain.QStore;
import umc.spring.domain.QRegion;

import java.util.List;

@RequiredArgsConstructor
public class UserMissionRepositoryImpl implements UserMissionRepositoryCustom {

    private final JPAQueryFactory query;
    private final QUserMission um = QUserMission.userMission;
    private final QMissionTemplate mt = QMissionTemplate.missionTemplate;
    private final QStore s = QStore.store;
    private final QRegion r = QRegion.region;

    @Override
    public Page<UserMissionSummaryDto> findMyMissions(Long userId,
                                                      List<MissionStatus> statuses,
                                                      Pageable pageable) {

        List<UserMissionSummaryDto> content = query
                .select(Projections.constructor(UserMissionSummaryDto.class,
                        um.id,
                        um.status.stringValue(),
                        um.assignedAt,
                        um.completedAt,
                        mt.condition,
                        mt.reward,
                        s.name,
                        r.name))
                .from(um)
                .join(um.missionTemplate, mt)
                .join(mt.store, s)
                .join(s.region, r)
                .where(um.user.id.eq(userId),
                        um.status.in(statuses))
                .orderBy(um.assignedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(um.count())
                .from(um)
                .where(um.user.id.eq(userId),
                        um.status.in(statuses))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}