package com.enjoytrip.members.controller;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    RegisterDto registerDto = new RegisterDto(email, nickname, marketing, password);
    LoginDto loginDto = new LoginDto(email, password);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("회원가입테스트")
    void register() {
        //then
        try {
            mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerDto))
            ).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("중복 이메일&아이디 가입 테스트")
    void register_fail() throws Exception {
        //then
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto))
        );

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto))
                ).andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("이메일과 닉네임이 중복되었습니다.")));
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto))
        );

        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/login")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());

        SessionDto sessionDto = (SessionDto) session.getAttribute("userinfo");
        Assertions.assertEquals(sessionDto.getEmail(), email);
        Assertions.assertEquals(sessionDto.getNickname(), nickname);
        Assertions.assertEquals(sessionDto.isMarketingAgreement(), marketing);
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void login_fail() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto))
        );

        LoginDto loginDtoF = new LoginDto(email, "fail");

        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/login")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDtoF)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("아이디 혹은 비밀번호가 틀렸습니다.")));
    }

    @Test
    @DisplayName("로그인 타입 실패 테스트")
    void login_fail_type() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto))
        );


        LoginDto loginDto1 = new LoginDto("123", "1 23");
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/login")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto1)))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    @DisplayName("중복 이메일 테스트-중복")
    void duplicated_email_test_중복() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto))
        );
        mockMvc.perform(get("/check-duplicate-email/test@test.com")
        ).andExpect(status().isOk())
                .andExpect(content().string(equalTo("중복")));

    }

    @Test
    @DisplayName("중복 이메일 테스트-사용가능")
    void duplicated_email_test_사용가능() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto))
        );
        mockMvc.perform(get("/check-duplicate-email/none@test.com")
                ).andExpect(status().isOk())
                .andExpect(content().string(equalTo("사용가능")));

    }
}