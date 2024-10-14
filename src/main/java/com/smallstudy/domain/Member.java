package com.smallstudy.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @Setter
    private String nickname;

    @Column(nullable = false)
    private String password;
    private String location;

    @Setter
    private LocalDateTime emailTokenReceivedAt;
    @Setter
    private String emailToken;

    @Setter
    private String imgPath;
    @Setter
    private String imgName;
    @Setter
    private String imgUuid;

    @Setter
    private String message;

    private boolean isEmailValid = false;
    public void setEmailValid() {isEmailValid = true;}

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public Member(String email, String nickname, String password, String token) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.emailToken = token;
    }

    public void copy(Member member) {
        this.id = member.id;
        this.email = member.email;
        this.location = member.location;
        this.nickname = member.nickname;
        this.password = member.password;
        this.emailToken = member.emailToken;
        this.isEmailValid = member.isEmailValid;
        this.imgName = member.imgName;
        this.imgPath = member.imgPath;
        this.imgUuid = member.imgUuid;
        this.message = member.message;
    }

    public void copyWithoutId(Member member) {
        this.email = member.email;
        this.location = member.location;
        this.nickname = member.nickname;
        this.password = member.password;
        this.emailToken = member.emailToken;
        this.isEmailValid = member.isEmailValid;
        this.imgName = member.imgName;
        this.imgPath = member.imgPath;
        this.imgUuid = member.imgUuid;
        this.message = member.message;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    List<MemberInterestRegion> memberInterestRegions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    List<MemberCategoryItem> memberCategoryItems;

}

