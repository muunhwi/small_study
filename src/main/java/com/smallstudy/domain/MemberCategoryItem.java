package com.smallstudy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCategoryItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_item_id")
    private CategoryItem categoryItem;

    public MemberCategoryItem(Member member, com.smallstudy.domain.CategoryItem categoryItem) {
        this.member = member;
        this.categoryItem = categoryItem;
    }
}