package com.enjoytrip.board.controller;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.board.service.BoardService;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService boardService;

    @Autowired
    MemberService memberService;

    ObjectMapper objectMapper = new ObjectMapper();
    int memberId = 0;

    @BeforeEach
    void before() throws SQLException {
        memberId = memberService.join(new RegisterDto("test@test", "test", true, "test"), "user");
    }


    @Test
    void detail() throws Exception {
        int id = boardService.register(new BoardRegisterDto("test", memberId, "test"));

        MvcResult mvcResult = mockMvc.perform(get("/board/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();
        BoardDto boardDto = objectMapper.convertValue(mvcResult.getResponse().getContentAsString(), BoardDto.class);
        System.out.println(boardDto);


    }

    @Test
    void list() {
    }

    @Test
    void register() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}