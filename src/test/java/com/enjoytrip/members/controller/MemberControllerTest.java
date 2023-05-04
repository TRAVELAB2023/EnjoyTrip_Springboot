package com.enjoytrip.members.controller;

import com.enjoytrip.members.dto.SessionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {
    String email = "test@test.com";
    String nickname = "test";
    String password = "test1234";
    boolean marketing = true;


    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("회원가입테스트")
    void register() {
        //then
        try {
            mockMvc.perform(post("/register")
                    .param("email", email)
                    .param("nickname", nickname)
                    .param("password", password)
                    .param("marketing", String.valueOf(marketing))
            ).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("중복 이메일 가입 테스트")
    void register_fail() throws Exception {
        //then
        mockMvc.perform(post("/register")
                .param("email", email)
                .param("nickname", nickname)
                .param("password", password)
                .param("marketing", String.valueOf(marketing))

        );
        mockMvc.perform(post("/register")
                .param("email", email)
                .param("nickname", nickname)
                .param("password", password)
                .param("marketing", String.valueOf(marketing))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {
        mockMvc.perform(post("/register")
                .param("email", email)
                .param("nickname", nickname)
                .param("password", password)
                .param("marketing", String.valueOf(marketing))
        );
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/login")
                        .session(session)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk())
                .andDo(print());

        SessionDto sessionDto = (SessionDto) session.getAttribute("userinfo");
        Assertions.assertEquals(sessionDto.getEmail(), email);
        Assertions.assertEquals(sessionDto.getNickname(), nickname);
        Assertions.assertEquals(sessionDto.isMarketingAgreement(), marketing);
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void login_fail() throws Exception {
        mockMvc.perform(post("/register")
                .param("email", email)
                .param("nickname", nickname)
                .param("password", password)
                .param("marketing", String.valueOf(marketing))
        );
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/login")
                        .session(session)
                        .param("email", "email")
                        .param("password", password))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}