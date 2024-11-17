package com.smallstudy.domain.region_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegion is a Querydsl query type for Region
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRegion extends BeanPath<Region> {

    private static final long serialVersionUID = -874084705L;

    public static final QRegion region = new QRegion("region");

    public final StringPath city = createString("city");

    public final StringPath dong = createString("dong");

    public final StringPath gu = createString("gu");

    public QRegion(String variable) {
        super(Region.class, forVariable(variable));
    }

    public QRegion(Path<? extends Region> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegion(PathMetadata metadata) {
        super(Region.class, metadata);
    }

}

