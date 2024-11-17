package com.smallstudy.domain.member_study_form_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberStudyFormQuestionTextAnswer is a Querydsl query type for MemberStudyFormQuestionTextAnswer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberStudyFormQuestionTextAnswer extends EntityPathBase<MemberStudyFormQuestionTextAnswer> {

    private static final long serialVersionUID = -1828367516L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberStudyFormQuestionTextAnswer memberStudyFormQuestionTextAnswer = new QMemberStudyFormQuestionTextAnswer("memberStudyFormQuestionTextAnswer");

    public final StringPath answer = createString("answer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberStudyForm memberStudyForm;

    public final com.smallstudy.domain.study_form_entity.QStudyFormQuestion studyFormQuestion;

    public QMemberStudyFormQuestionTextAnswer(String variable) {
        this(MemberStudyFormQuestionTextAnswer.class, forVariable(variable), INITS);
    }

    public QMemberStudyFormQuestionTextAnswer(Path<? extends MemberStudyFormQuestionTextAnswer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberStudyFormQuestionTextAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberStudyFormQuestionTextAnswer(PathMetadata metadata, PathInits inits) {
        this(MemberStudyFormQuestionTextAnswer.class, metadata, inits);
    }

    public QMemberStudyFormQuestionTextAnswer(Class<? extends MemberStudyFormQuestionTextAnswer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberStudyForm = inits.isInitialized("memberStudyForm") ? new QMemberStudyForm(forProperty("memberStudyForm"), inits.get("memberStudyForm")) : null;
        this.studyFormQuestion = inits.isInitialized("studyFormQuestion") ? new com.smallstudy.domain.study_form_entity.QStudyFormQuestion(forProperty("studyFormQuestion"), inits.get("studyFormQuestion")) : null;
    }

}

