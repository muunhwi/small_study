package com.smallstudy.domain.member_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1001976683L;

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath emailToken = createString("emailToken");

    public final DateTimePath<java.time.LocalDateTime> emailTokenReceivedAt = createDateTime("emailTokenReceivedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgPath = createString("imgPath");

    public final StringPath imgUuid = createString("imgUuid");

    public final BooleanPath isEmailValid = createBoolean("isEmailValid");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public final StringPath location = createString("location");

    public final ListPath<MemberCategoryItem, QMemberCategoryItem> memberCategoryItems = this.<MemberCategoryItem, QMemberCategoryItem>createList("memberCategoryItems", MemberCategoryItem.class, QMemberCategoryItem.class, PathInits.DIRECT2);

    public final ListPath<MemberInterestRegion, QMemberInterestRegion> memberInterestRegions = this.<MemberInterestRegion, QMemberInterestRegion>createList("memberInterestRegions", MemberInterestRegion.class, QMemberInterestRegion.class, PathInits.DIRECT2);

    public final StringPath message = createString("message");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

