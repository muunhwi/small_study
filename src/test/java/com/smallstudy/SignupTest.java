package com.smallstudy;

import com.smallstudy.domain.member_entity.Member;
import com.smallstudy.repo.member_repo.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SignupTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;

    /*
    * 회원가입 -> 이메일 인증 코드 발급 -> 회원가입 -> 로그인 페이지로 리다이렉트
    *
    */
    @DisplayName("이메일토큰 - 토큰 발급")
    @Test
    void 이메일토큰정상발급() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test@gmail3.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.msg").value("이메일로 토큰이 전송 되었습니다."));

    }

    @DisplayName("이메일토큰 - 토큰 발급, 이메일 형식 올바르지 않음")
    @Test
    void 이메일토큰발급_이메일형식_올바르지않음() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(100))
                .andExpect(jsonPath("$.msg").value("이메일 형식이 올바르지 않습니다."));
    }

    @DisplayName("이메일토큰 - 토큰 발급, 이메일 중복")
    @Test
    void 이메일토큰발급_이메일_중복() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test@google.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(101))
                .andExpect(jsonPath("$.msg").value("중복된 이메일 입니다."));
    }

    @DisplayName("이메일토큰 - 10분 이전 다시 요청")
    @Test
    void 십분이전다시요청() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test@gmail2.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.msg").value("이메일로 토큰이 전송 되었습니다."));

        Optional<Member> findMember = memberRepository.findByEmail("test@gmail.com");
        Assertions.assertTrue(findMember.isPresent(), "맴버는 널이 아니여야 한다.");

        mockMvc.perform(post("/email-token")
                        .param("username", "test@gmail2.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(102))
                .andExpect(jsonPath("$.msg").value("10분후 다시 토큰 발급이 가능합니다."));
    }

    @DisplayName("이메일토큰 - 10분 이후 다시 요청")
    @Test
    void 십분이후다시요청() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test@gmail.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.msg").value("이메일로 토큰이 전송 되었습니다."));

        Optional<Member> findMember = memberRepository.findByEmail("test@gmail.com");
        Assertions.assertTrue(findMember.isPresent(), "맴버는 널이 아니여야 한다.");

        Member member = findMember.get();
        member.setEmailTokenReceivedAt(member.getEmailTokenReceivedAt().minusMinutes(10));
        memberRepository.save(member);


        mockMvc.perform(post("/email-token")
                        .param("username", "test@gmail.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0))
                .andExpect(jsonPath("$.msg").value("이메일로 토큰이 전송 되었습니다."));
    }

    @DisplayName("회원가입 - 토큰 인증 없이 회원가입")
    @Test
    void 토큰없이_회원가입() throws Exception
    {
        mockMvc.perform(post("/signup")
                        .param("username", "test2@google.com")
                        .param("password", "test0123!")
                        .param("nickname", "test")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("form", "emailToken"));
    }

    @DisplayName("회원가입 - 정상흐름")
    @Test
    void 정상회원가입() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test4@gmail.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));

        Optional<Member> findMember = memberRepository.findByEmail("test4@gmail.com");
        Assertions.assertTrue(findMember.isPresent(), "맴버는 널이 아니여야 한다.");

        Member member = findMember.get();
        mockMvc.perform(post("/signup")
                        .param("username", "test4@gmail.com")
                        .param("password", "test0123!")
                        .param("nickname", "test")
                        .param("emailToken", member.getEmailToken())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));

    }

    @DisplayName("회원가입 - 올바르지 않은 토큰")
    @Test
    void 올바르지_않은_토큰_회원가입() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test3@gmail.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));

        Optional<Member> findMember = memberRepository.findByEmail("test3@gmail.com");
        Assertions.assertTrue(findMember.isPresent(), "맴버는 널이 아니여야 한다.");

        Member member = findMember.get();
        mockMvc.perform(post("/signup")
                        .param("username", "test3@gmail.com")
                        .param("password", "test0123!")
                        .param("nickname", "test")
                        .param("emailToken", "asdada123")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("form", "emailToken"));

    }

    @DisplayName("회원가입 - 중복 닉네임")
    @Test
    void 중복닉네임() throws Exception
    {
        mockMvc.perform(post("/email-token")
                        .param("username", "test2@gmail.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorCode").value(0));

        Optional<Member> findMember = memberRepository.findByEmail("test2@gmail.com");
        Assertions.assertTrue(findMember.isPresent(), "맴버는 널이 아니여야 한다.");

        Member member = findMember.get();
        mockMvc.perform(post("/signup")
                        .param("username", "test2@gmail.com")
                        .param("password", "test0123!")
                        .param("nickname", "admin")
                        .param("emailToken", member.getEmailToken())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("form", "nickname"));

    }





}