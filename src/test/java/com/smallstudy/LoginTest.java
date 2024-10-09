package com.smallstudy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;


    /*
    * 이메일이 유효한가?
    * 비밀번호가 유효한가?
    * 이메일이 다른경우?
    * 비밀번호가 다른경우?
    * */

    @DisplayName("로그인 - 성공")
    @Test
    void 성공로그인() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "test@google.com")
                        .param("password", "dwc02207!")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main"));
    }

    @DisplayName("로그인 - 이메일이 유효하지 않음")
    @Test
    void 이메일_유효하지않음() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "test")
                .param("password", "dwc02207!")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));

    }

    @DisplayName("로그인 - 비밀번호가 유효하지 않음")
    @Test
    void 비밀번호_유효하지않음() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "test@google.com")
                        .param("password", "test01")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @DisplayName("로그인 - 이메일이 존재하지 않음")
    @Test
    void 이메일_존재하지않음() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "test@gmail.com")
                        .param("password", "dwc02207!")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @DisplayName("로그인 - 비밀번호가 존재하지 않음")
    @Test
    void 비밀번호_존재하지않음() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "test@google.com")
                        .param("password", "test12301!")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }


}
