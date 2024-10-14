package com.smallstudy.service;


import com.smallstudy.domain.Member;
import com.smallstudy.dto.FileDTO;
import com.smallstudy.dto.ProfileDTO;
import com.smallstudy.error.UnSupportedImageFileTypeException;
import com.smallstudy.repo.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberService {


    private final MultipartFileService multipartFileService;
    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;


    public boolean duplicatedEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);

        if(findMember.isEmpty())
            return false;

        Member member = findMember.get();
        return member.isEmailValid();
    }

    public boolean duplicatedNickname(String nickname) {
        return (memberRepository.countByNickname(nickname) > 0);
    }


    @Transactional
    public void sendEmailTokenAndTemporarySave(String email) {

        String token = UUID.randomUUID().toString().substring(0, 8);

        Optional<Member> findMember = memberRepository.findByEmail(email);
        Member member = findMember.orElseGet(() -> new Member(email, ""));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("test@gmail.com");
        message.setSubject("스몰 스터디 이메일 확인 메일");
        message.setText(String.format("토큰 코드 : [%s] 복사하여 입력해주세요!", token));
        mailSender.send(message);

        member.setEmailToken(token);
        member.setEmailTokenReceivedAt(LocalDateTime.now());
    }

    @Transactional
    public boolean emailValidAndSignup(Member unverifiedMember) {

        Optional<Member> findMember = memberRepository.findByEmail(unverifiedMember.getEmail());
        if(findMember.isEmpty())
            return true;

        Member member = findMember.get();
        String token = member.getEmailToken();

        if(Objects.isNull(token))
            return true;

        if(token.equals(unverifiedMember.getEmailToken())) {
            member.copyWithoutId(unverifiedMember);
            member.setEmailValid();
        } else {
            memberRepository.delete(member);
            return true;
        }

        return false;
    }


    public boolean canResendEmailToken(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if(findMember.isEmpty()) return true;

        Member member = findMember.get();
        LocalDateTime tenPlus = member.getEmailTokenReceivedAt().plusMinutes(10);
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(tenPlus);
    }

    @Transactional
    public Member updateMemberProfile(ProfileDTO dto) throws IOException {

        Optional<Member> findMember = memberRepository.findByEmail(dto.username);
        findMember.orElseThrow(() -> new UsernameNotFoundException("해당 하는 이메일이 존재하지 않습니다."));

        Member member = findMember.get();
        member.setNickname(dto.nickname);
        member.setMessage(dto.message);

        MultipartFile multipartFile = dto.profileImage;
        if(!multipartFile.isEmpty()) {

            FileDTO fileDTO = multipartFileService.saveImgFile(multipartFile);
            member.setImgName(fileDTO.originalName);
            member.setImgUuid(fileDTO.uuid);
            member.setImgPath(fileDTO.path);
        }
        return member;
    }
}
