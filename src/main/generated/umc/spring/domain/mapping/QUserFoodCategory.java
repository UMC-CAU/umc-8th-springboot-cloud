package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFoodCategory is a Querydsl query type for UserFoodCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFoodCategory extends EntityPathBase<UserFoodCategory> {

    private static final long serialVersionUID = -420203341L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFoodCategory userFoodCategory = new QUserFoodCategory("userFoodCategory");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final umc.spring.domain.QFoodCategory foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final umc.spring.domain.QUser user;

    public QUserFoodCategory(String variable) {
        this(UserFoodCategory.class, forVariable(variable), INITS);
    }

    public QUserFoodCategory(Path<? extends UserFoodCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFoodCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFoodCategory(PathMetadata metadata, PathInits inits) {
        this(UserFoodCategory.class, metadata, inits);
    }

    public QUserFoodCategory(Class<? extends UserFoodCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new umc.spring.domain.QFoodCategory(forProperty("foodCategory")) : null;
        this.user = inits.isInitialized("user") ? new umc.spring.domain.QUser(forProperty("user")) : null;
    }

}

