package com.enjoytrip.notice.controller;

import com.enjoytrip.exception.custom_exception.WrongPageException;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.model.Notice;
import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.notice.dto.NoticeSearchDto;
import com.enjoytrip.notice.service.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NoticeControllerTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NoticeService noticeService;

    ObjectMapper objectMapper = new ObjectMapper();



    static int adminId = 0;
    @BeforeEach
    public void setup() throws SQLException {
        adminId = memberService.join(new RegisterDto("test@test", "test", true, "test"), "admin");
    }



    @Test
    void detail() throws Exception {
        NoticeRegisterDto noticeRegisterDto = new NoticeRegisterDto("test", adminId, "test");
        int noticeId = noticeService.register(noticeRegisterDto);

        MvcResult mvcResult = mockMvc.perform(get("/notice/"+noticeId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print()).andReturn();

        NoticeDetailDto noticeDetailDto = objectMapper.registerModule(new JavaTimeModule()).readValue(mvcResult.getResponse().getContentAsString(),NoticeDetailDto.class);
        Assertions.assertEquals(noticeDetailDto.getContent(),noticeRegisterDto.getContent());
    }

    @Test
    void list() throws Exception {
        for (int i = 1; i < 100; i++) {
            noticeService.register(new NoticeRegisterDto(i+"", adminId, i+""));
        }
        NoticeSearchDto noticeSearchDto = new NoticeSearchDto("",0,0,10);

        MvcResult mvcResult = mockMvc.perform(get("/notice/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noticeSearchDto))
        ).andExpect(status().isOk()).andReturn();
        List<NoticeListDto> list =objectMapper.registerModule(new JavaTimeModule()).readValue(mvcResult.getResponse().getContentAsString(),List.class);
        Assertions.assertEquals(list.size(),noticeSearchDto.getSize());
    }

    @Test
    void register() throws Exception {
        NoticeRegisterDto noticeRegisterDto = new NoticeRegisterDto("test", adminId, "test");
        int noticeId = 0;
        MvcResult mvcResult = mockMvc.perform(post("/notice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noticeRegisterDto))
        ).andExpect(status().isOk()).andReturn();
        noticeId = Integer.parseInt(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(noticeService.detail(noticeId).getContent(), noticeRegisterDto.getContent());
    }

    @Test
    void delete() throws Exception {
        int id = noticeService.register(new NoticeRegisterDto("test", adminId, "test"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/notice/"+id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        Assertions.assertThrows(WrongPageException.class, ()-> noticeService.detail(id));
    }
}