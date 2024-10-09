package com.smallstudy.service;


import com.smallstudy.domain.Member;
import com.smallstudy.repo.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;


    public boolean duplicatedEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);

        if(findMember.isEmpty())
            return false;

        Member member = findMember.get();
        if(member.isEmailValid())
            return true;

        return false;
    }


    @Transactional
    public void sendEmailTokenAndTemporarySave(String email) {

        String token = UUID.randomUUID().toString().substring(0, 8);

        Optional<Member> findMember = memberRepository.findByEmail(email);
        Member member = findMember.orElseGet(() -> new Member(email, ""));

        member.setEmailToken(token);
        member.setEmailTokenReceivedAt(LocalDateTime.now());
        memberRepository.save(member);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("test@gmail.com");
        message.setSubject("스몰 스터디 이메일 확인 메일");
        message.setText(String.format("토큰 코드 : [%s] 복사하여 입력해주세요!", token));
        mailSender.send(message);
    }

    @Transactional
    public boolean emailValidAndSignup(Member unverifiedMember) {

        Optional<Member> findMember = memberRepository.findByEmail(unverifiedMember.getEmail());
        if(findMember.isEmpty())
            return false;

        Member member = findMember.get();
        String token = member.getEmailToken();

        if(Objects.isNull(token))
            return false;

        if(token.equals(unverifiedMember.getEmailToken())) {
            member.copy(unverifiedMember);
            member.setEmailValid();
        } else {
            memberRepository.delete(member);
            return false;
        }

        return true;
    }


    public boolean canResendEmailToken(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if(findMember.isEmpty()) return true;

        Member member = findMember.get();
        LocalDateTime tenAfter = member.getEmailTokenReceivedAt().plusMinutes(10);
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(tenAfter);
    }
}
