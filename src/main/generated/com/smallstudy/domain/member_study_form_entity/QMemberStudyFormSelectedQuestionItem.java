package com.smallstudy.domain.member_study_form_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberStudyFormSelectedQuestionItem is a Querydsl query type for MemberStudyFormSelectedQuestionItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberStudyFormSelectedQuestionItem extends EntityPathBase<MemberStudyFormSelectedQuestionItem> {

    private static final long serialVersionUID = -1279218393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberStudyFormSelectedQuestionItem memberStudyFormSelectedQuestionItem = new QMemberStudyFormSelectedQuestionItem("memberStudyFormSelectedQuestionItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberStudyForm memberStudyForm;

    public final com.smallstudy.domain.study_form_entity.QStudyFormQuestion studyFormQuestion;

    public final com.smallstudy.domain.study_form_entity.QStudyFormQuestionItem studyFormQuestionItem;

    public QMemberStudyFormSelectedQuestionItem(String variable) {
        this(MemberStudyFormSelectedQuestionItem.class, forVariable(variable), INITS);
    }

    public QMemberStudyFormSelectedQuestionItem(Path<? extends MemberStudyFormSelectedQuestionItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberStudyFormSelectedQuestionItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberStudyFormSelectedQuestionItem(PathMetadata metadata, PathInits inits) {
        this(MemberStudyFormSelectedQuestionItem.class, metadata, inits);
    }

    public QMemberStudyFormSelectedQuestionItem(Class<? extends MemberStudyFormSelectedQuestionItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberStudyForm = inits.isInitialized("memberStudyForm") ? new QMemberStudyForm(forProperty("memberStudyForm"), inits.get("memberStudyForm")) : null;
        this.studyFormQuestion = inits.isInitialized("studyFormQuestion") ? new com.smallstudy.domain.study_form_entity.QStudyFormQuestion(forProperty("studyFormQuestion"), inits.get("studyFormQuestion")) : null;
        this.studyFormQuestionItem = inits.isInitialized("studyFormQuestionItem") ? new com.smallstudy.domain.study_form_entity.QStudyFormQuestionItem(forProperty("studyFormQuestionItem"), inits.get("studyFormQuestionItem")) : null;
    }

}

