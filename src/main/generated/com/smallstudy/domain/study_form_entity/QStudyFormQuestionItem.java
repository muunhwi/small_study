package com.smallstudy.domain.study_form_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyFormQuestionItem is a Querydsl query type for StudyFormQuestionItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyFormQuestionItem extends EntityPathBase<StudyFormQuestionItem> {

    private static final long serialVersionUID = 908313749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyFormQuestionItem studyFormQuestionItem = new QStudyFormQuestionItem("studyFormQuestionItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath item = createString("item");

    public final QStudyFormQuestion studyFormQuestion;

    public QStudyFormQuestionItem(String variable) {
        this(StudyFormQuestionItem.class, forVariable(variable), INITS);
    }

    public QStudyFormQuestionItem(Path<? extends StudyFormQuestionItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyFormQuestionItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyFormQuestionItem(PathMetadata metadata, PathInits inits) {
        this(StudyFormQuestionItem.class, metadata, inits);
    }

    public QStudyFormQuestionItem(Class<? extends StudyFormQuestionItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyFormQuestion = inits.isInitialized("studyFormQuestion") ? new QStudyFormQuestion(forProperty("studyFormQuestion"), inits.get("studyFormQuestion")) : null;
    }

}

