package com.smallstudy.domain.study_form_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyForm is a Querydsl query type for StudyForm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyForm extends EntityPathBase<StudyForm> {

    private static final long serialVersionUID = 359944796L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyForm studyForm = new QStudyForm("studyForm");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public final ListPath<com.smallstudy.domain.member_study_form_entity.MemberStudyForm, com.smallstudy.domain.member_study_form_entity.QMemberStudyForm> memberStudyForms = this.<com.smallstudy.domain.member_study_form_entity.MemberStudyForm, com.smallstudy.domain.member_study_form_entity.QMemberStudyForm>createList("memberStudyForms", com.smallstudy.domain.member_study_form_entity.MemberStudyForm.class, com.smallstudy.domain.member_study_form_entity.QMemberStudyForm.class, PathInits.DIRECT2);

    public final com.smallstudy.domain.study_entity.QStudy study;

    public final ListPath<StudyFormQuestion, QStudyFormQuestion> studyFormQuestions = this.<StudyFormQuestion, QStudyFormQuestion>createList("studyFormQuestions", StudyFormQuestion.class, QStudyFormQuestion.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QStudyForm(String variable) {
        this(StudyForm.class, forVariable(variable), INITS);
    }

    public QStudyForm(Path<? extends StudyForm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyForm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyForm(PathMetadata metadata, PathInits inits) {
        this(StudyForm.class, metadata, inits);
    }

    public QStudyForm(Class<? extends StudyForm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new com.smallstudy.domain.study_entity.QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

