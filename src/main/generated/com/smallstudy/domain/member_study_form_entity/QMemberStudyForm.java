package com.smallstudy.domain.member_study_form_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberStudyForm is a Querydsl query type for MemberStudyForm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberStudyForm extends EntityPathBase<MemberStudyForm> {

    private static final long serialVersionUID = -931425741L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberStudyForm memberStudyForm = new QMemberStudyForm("memberStudyForm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.smallstudy.domain.member_entity.QMember member;

    public final ListPath<MemberStudyFormQuestionTextAnswer, QMemberStudyFormQuestionTextAnswer> memberStudyFormQuestionTextAnswers = this.<MemberStudyFormQuestionTextAnswer, QMemberStudyFormQuestionTextAnswer>createList("memberStudyFormQuestionTextAnswers", MemberStudyFormQuestionTextAnswer.class, QMemberStudyFormQuestionTextAnswer.class, PathInits.DIRECT2);

    public final ListPath<MemberStudyFormSelectedQuestionItem, QMemberStudyFormSelectedQuestionItem> memberStudyFormSelectedQuestionItems = this.<MemberStudyFormSelectedQuestionItem, QMemberStudyFormSelectedQuestionItem>createList("memberStudyFormSelectedQuestionItems", MemberStudyFormSelectedQuestionItem.class, QMemberStudyFormSelectedQuestionItem.class, PathInits.DIRECT2);

    public final com.smallstudy.domain.study_form_entity.QStudyForm studyForm;

    public QMemberStudyForm(String variable) {
        this(MemberStudyForm.class, forVariable(variable), INITS);
    }

    public QMemberStudyForm(Path<? extends MemberStudyForm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberStudyForm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberStudyForm(PathMetadata metadata, PathInits inits) {
        this(MemberStudyForm.class, metadata, inits);
    }

    public QMemberStudyForm(Class<? extends MemberStudyForm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.smallstudy.domain.member_entity.QMember(forProperty("member")) : null;
        this.studyForm = inits.isInitialized("studyForm") ? new com.smallstudy.domain.study_form_entity.QStudyForm(forProperty("studyForm"), inits.get("studyForm")) : null;
    }

}

