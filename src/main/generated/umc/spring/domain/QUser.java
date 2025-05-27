package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1348045193L;

    public static final QUser user = new QUser("user");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc.spring.domain.enums.Gender> gender = createEnum("gender", umc.spring.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final ListPath<Inquiry, QInquiry> inquiryList = this.<Inquiry, QInquiry>createList("inquiryList", Inquiry.class, QInquiry.class, PathInits.DIRECT2);

    public final ListPath<umc.spring.domain.mapping.UserMission, umc.spring.domain.mapping.QUserMission> missions = this.<umc.spring.domain.mapping.UserMission, umc.spring.domain.mapping.QUserMission>createList("missions", umc.spring.domain.mapping.UserMission.class, umc.spring.domain.mapping.QUserMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<umc.spring.domain.mapping.UserFoodCategory, umc.spring.domain.mapping.QUserFoodCategory> preferCategories = this.<umc.spring.domain.mapping.UserFoodCategory, umc.spring.domain.mapping.QUserFoodCategory>createList("preferCategories", umc.spring.domain.mapping.UserFoodCategory.class, umc.spring.domain.mapping.QUserFoodCategory.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<umc.spring.domain.enums.UserStatus> status = createEnum("status", umc.spring.domain.enums.UserStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<UserSocial, QUserSocial> userSocialList = this.<UserSocial, QUserSocial>createList("userSocialList", UserSocial.class, QUserSocial.class, PathInits.DIRECT2);

    public final ListPath<umc.spring.domain.mapping.UserTerms, umc.spring.domain.mapping.QUserTerms> userTermsList = this.<umc.spring.domain.mapping.UserTerms, umc.spring.domain.mapping.QUserTerms>createList("userTermsList", umc.spring.domain.mapping.UserTerms.class, umc.spring.domain.mapping.QUserTerms.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

