package com.smallstudy.domain;

import com.smallstudy.dto.SignupDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(String email, String nickname, String password, String token) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.emailToken = token;
    }

    public Member(Member member) {
        copy(member);
    }

    public void copy(Member member) {
        this.email = member.email;
        this.location = member.location;
        this.nickname = member.nickname;
        this.password = member.password;
        this.emailToken = member.emailToken;
    }

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;
    private String nickname;

    private String password;
    private String location;

    @Setter
    private LocalDateTime emailTokenReceivedAt;
    @Setter
    private String emailToken;

    private boolean isEmailValid = false;

    public void setEmailValid() {isEmailValid = true;}

}
