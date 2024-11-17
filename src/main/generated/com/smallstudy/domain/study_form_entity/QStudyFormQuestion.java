package com.smallstudy.domain.study_form_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyFormQuestion is a Querydsl query type for StudyFormQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyFormQuestion extends EntityPathBase<StudyFormQuestion> {

    private static final long serialVersionUID = 1408685666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyFormQuestion studyFormQuestion = new QStudyFormQuestion("studyFormQuestion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath question = createString("question");

    public final QStudyForm studyForm;

    public final ListPath<StudyFormQuestionItem, QStudyFormQuestionItem> studyFormQuestionItems = this.<StudyFormQuestionItem, QStudyFormQuestionItem>createList("studyFormQuestionItems", StudyFormQuestionItem.class, QStudyFormQuestionItem.class, PathInits.DIRECT2);

    public final EnumPath<QuestionType> type = createEnum("type", QuestionType.class);

    public QStudyFormQuestion(String variable) {
        this(StudyFormQuestion.class, forVariable(variable), INITS);
    }

    public QStudyFormQuestion(Path<? extends StudyFormQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyFormQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyFormQuestion(PathMetadata metadata, PathInits inits) {
        this(StudyFormQuestion.class, metadata, inits);
    }

    public QStudyFormQuestion(Class<? extends StudyFormQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyForm = inits.isInitialized("studyForm") ? new QStudyForm(forProperty("studyForm"), inits.get("studyForm")) : null;
    }

}

