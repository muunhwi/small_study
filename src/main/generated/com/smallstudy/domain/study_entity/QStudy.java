package com.smallstudy.domain.study_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -215557505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final ListPath<com.smallstudy.domain.member_entity.Application, com.smallstudy.domain.member_entity.QApplication> applications = this.<com.smallstudy.domain.member_entity.Application, com.smallstudy.domain.member_entity.QApplication>createList("applications", com.smallstudy.domain.member_entity.Application.class, com.smallstudy.domain.member_entity.QApplication.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> groupSize = createNumber("groupSize", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.smallstudy.domain.region_entity.QInterestRegion interestRegion;

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public final com.smallstudy.domain.member_entity.QMember member;

    public final ListPath<StudyCategoryItem, QStudyCategoryItem> studyCategoryItems = this.<StudyCategoryItem, QStudyCategoryItem>createList("studyCategoryItems", StudyCategoryItem.class, QStudyCategoryItem.class, PathInits.DIRECT2);

    public final ListPath<StudyFile, QStudyFile> studyFiles = this.<StudyFile, QStudyFile>createList("studyFiles", StudyFile.class, QStudyFile.class, PathInits.DIRECT2);

    public final com.smallstudy.domain.study_form_entity.QStudyForm studyForm;

    public final StringPath title = createString("title");

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy(PathMetadata metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interestRegion = inits.isInitialized("interestRegion") ? new com.smallstudy.domain.region_entity.QInterestRegion(forProperty("interestRegion"), inits.get("interestRegion")) : null;
        this.member = inits.isInitialized("member") ? new com.smallstudy.domain.member_entity.QMember(forProperty("member")) : null;
        this.studyForm = inits.isInitialized("studyForm") ? new com.smallstudy.domain.study_form_entity.QStudyForm(forProperty("studyForm"), inits.get("studyForm")) : null;
    }

}

