package com.smallstudy.domain.region_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestRegion is a Querydsl query type for InterestRegion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestRegion extends EntityPathBase<InterestRegion> {

    private static final long serialVersionUID = -882985111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestRegion interestRegion = new QInterestRegion("interestRegion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRegion region;

    public QInterestRegion(String variable) {
        this(InterestRegion.class, forVariable(variable), INITS);
    }

    public QInterestRegion(Path<? extends InterestRegion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestRegion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestRegion(PathMetadata metadata, PathInits inits) {
        this(InterestRegion.class, metadata, inits);
    }

    public QInterestRegion(Class<? extends InterestRegion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region")) : null;
    }

}

