package com.smallstudy.domain.study_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyCategoryItem is a Querydsl query type for StudyCategoryItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyCategoryItem extends EntityPathBase<StudyCategoryItem> {

    private static final long serialVersionUID = 1943954768L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyCategoryItem studyCategoryItem = new QStudyCategoryItem("studyCategoryItem");

    public final com.smallstudy.domain.category_entity.QCategoryItem categoryItem;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudy study;

    public QStudyCategoryItem(String variable) {
        this(StudyCategoryItem.class, forVariable(variable), INITS);
    }

    public QStudyCategoryItem(Path<? extends StudyCategoryItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyCategoryItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyCategoryItem(PathMetadata metadata, PathInits inits) {
        this(StudyCategoryItem.class, metadata, inits);
    }

    public QStudyCategoryItem(Class<? extends StudyCategoryItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryItem = inits.isInitialized("categoryItem") ? new com.smallstudy.domain.category_entity.QCategoryItem(forProperty("categoryItem"), inits.get("categoryItem")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

