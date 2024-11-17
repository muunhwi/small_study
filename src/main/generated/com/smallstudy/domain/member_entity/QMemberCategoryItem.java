package com.smallstudy.domain.member_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCategoryItem is a Querydsl query type for MemberCategoryItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCategoryItem extends EntityPathBase<MemberCategoryItem> {

    private static final long serialVersionUID = -1697219524L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberCategoryItem memberCategoryItem = new QMemberCategoryItem("memberCategoryItem");

    public final com.smallstudy.domain.category_entity.QCategoryItem categoryItem;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QMemberCategoryItem(String variable) {
        this(MemberCategoryItem.class, forVariable(variable), INITS);
    }

    public QMemberCategoryItem(Path<? extends MemberCategoryItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberCategoryItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberCategoryItem(PathMetadata metadata, PathInits inits) {
        this(MemberCategoryItem.class, metadata, inits);
    }

    public QMemberCategoryItem(Class<? extends MemberCategoryItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryItem = inits.isInitialized("categoryItem") ? new com.smallstudy.domain.category_entity.QCategoryItem(forProperty("categoryItem"), inits.get("categoryItem")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

