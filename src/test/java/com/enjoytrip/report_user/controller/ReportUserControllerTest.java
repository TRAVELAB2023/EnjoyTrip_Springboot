package com.enjoytrip.report_user.controller;

import com.enjoytrip.report_user.dto.ReportUserDto;
import com.enjoytrip.report_user.dto.ReportUserRegisterDto;
import com.enjoytrip.report_user.service.ReportUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReportUserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ReportUserService reportUserService;

    ObjectMapper objectMapper = new ObjectMapper();

    ReportUserRegisterDto reportUserRegisterDto = new ReportUserRegisterDto(27, 28, 1);


    @Test
    void reportUser() throws Exception {
        List<ReportUserDto> list= reportUserService.findReportUserByDoYn(false);


        mockMvc.perform(post("/report-user/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportUserRegisterDto))
        ).andExpect(status().isOk());
        assertEquals(list.size()+1,reportUserService.findReportUserByDoYn(false).size());
    }
}