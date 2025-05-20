package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionTemplate is a Querydsl query type for MissionTemplate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionTemplate extends EntityPathBase<MissionTemplate> {

    private static final long serialVersionUID = 2078324730L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionTemplate missionTemplate = new QMissionTemplate("missionTemplate");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final StringPath condition = createString("condition");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reward = createString("reward");

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<umc.spring.domain.mapping.UserMission, umc.spring.domain.mapping.QUserMission> userMissions = this.<umc.spring.domain.mapping.UserMission, umc.spring.domain.mapping.QUserMission>createList("userMissions", umc.spring.domain.mapping.UserMission.class, umc.spring.domain.mapping.QUserMission.class, PathInits.DIRECT2);

    public QMissionTemplate(String variable) {
        this(MissionTemplate.class, forVariable(variable), INITS);
    }

    public QMissionTemplate(Path<? extends MissionTemplate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionTemplate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionTemplate(PathMetadata metadata, PathInits inits) {
        this(MissionTemplate.class, metadata, inits);
    }

    public QMissionTemplate(Class<? extends MissionTemplate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

