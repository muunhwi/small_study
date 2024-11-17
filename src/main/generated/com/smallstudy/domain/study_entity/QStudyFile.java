package com.smallstudy.domain.study_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyFile is a Querydsl query type for StudyFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyFile extends EntityPathBase<StudyFile> {

    private static final long serialVersionUID = -146215781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyFile studyFile = new QStudyFile("studyFile");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final StringPath imgUuid = createString("imgUuid");

    public final QStudy study;

    public QStudyFile(String variable) {
        this(StudyFile.class, forVariable(variable), INITS);
    }

    public QStudyFile(Path<? extends StudyFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyFile(PathMetadata metadata, PathInits inits) {
        this(StudyFile.class, metadata, inits);
    }

    public QStudyFile(Class<? extends StudyFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

