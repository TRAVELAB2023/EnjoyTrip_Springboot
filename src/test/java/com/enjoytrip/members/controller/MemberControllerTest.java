package com.enjoytrip.members.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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

    @Autowired
    MockMvc mockMvc;

    @Test
    void register() {
        //given
        String email = "test@test.com";
        String nickname = "test";
        String password = "test1234";
        boolean marketing = true;

        //when
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
    void register_fail() {
        //given
        String email = "test@test.com";
        String nickname = "test";
        String password = "test1234";
        boolean marketing = true;

        //when

        //then
        try {
            mockMvc.perform(post("/register")
                    .param("email", email)
                    .param("nickname", nickname)
                    .param("password", password)
                    .param("marketing", String.valueOf(marketing))

            ).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mockMvc.perform(post("/register")
                    .param("email", email)
                    .param("nickname", nickname)
                    .param("password", password)
                    .param("marketing", String.valueOf(marketing))

            ).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void login() throws Exception {
        String email = "test@test.com";
        String nickname = "test";
        String password = "test1234";
        boolean marketing = true;


        mockMvc.perform(post("/register")
                .param("email", email)
                .param("nickname", nickname)
                .param("password", password)
                .param("marketing", String.valueOf(marketing))
        );

        mockMvc.perform(post("/login")
                .param("email", email)
                .param("password", password)
        ).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"email\":\"test@test.com\"")))
                .andExpect(content().string(containsString("\"nickname\":\"test\"")))
                .andExpect(content().string(containsString("\"marketingAgreement\":true")))
                .andExpect(content().string(containsString("\"role\":\"user\"")))
                ;


    }
}