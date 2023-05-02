package com.enjoytrip;

import com.enjoytrip.model.Sido;
import com.enjoytrip.sido.controller.SidoController;
import com.enjoytrip.sido.dto.SidoDto;
import com.enjoytrip.sido.service.SidoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SidoController.class)
public class SidoControllerTest {


    private Logger logger= LoggerFactory.getLogger(SidoController.class);

    MockMvc mockMvc;


    @Autowired
    SidoController sidoController;
    @MockBean
    SidoService sidoService;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(sidoController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))  // 한글 깨짐 처리
                .build();
    }



    @Test
    @DisplayName("대한민국 도 조회")
    public void getSido() throws Exception {
        given(sidoService.getSidoList())
                .willReturn(Arrays.asList(new SidoDto(Sido.builder().sidoName("서울").sidoCode(1).build())));

        mockMvc.perform(get("/sido"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"sidoCode\":1,\"sidoName\":\"서울\"}"))
                .andDo(print());
    }
}
