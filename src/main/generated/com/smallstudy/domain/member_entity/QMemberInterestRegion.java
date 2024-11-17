package com.smallstudy.domain.member_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberInterestRegion is a Querydsl query type for MemberInterestRegion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberInterestRegion extends EntityPathBase<MemberInterestRegion> {

    private static final long serialVersionUID = -915858679L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberInterestRegion memberInterestRegion = new QMemberInterestRegion("memberInterestRegion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.smallstudy.domain.region_entity.QInterestRegion interestRegion;

    public final QMember member;

    public QMemberInterestRegion(String variable) {
        this(MemberInterestRegion.class, forVariable(variable), INITS);
    }

    public QMemberInterestRegion(Path<? extends MemberInterestRegion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberInterestRegion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberInterestRegion(PathMetadata metadata, PathInits inits) {
        this(MemberInterestRegion.class, metadata, inits);
    }

    public QMemberInterestRegion(Class<? extends MemberInterestRegion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interestRegion = inits.isInitialized("interestRegion") ? new com.smallstudy.domain.region_entity.QInterestRegion(forProperty("interestRegion"), inits.get("interestRegion")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

