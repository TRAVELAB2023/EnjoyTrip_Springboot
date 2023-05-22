package com.enjoytrip.Attraction;

import com.enjoytrip.attraction.controller.AttractionController;
import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.SearchCondition;
import com.enjoytrip.model.Sido;
import com.enjoytrip.sido.controller.SidoController;
import com.enjoytrip.sido.dto.SidoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
public class AttractionControllerTest {


    private Logger logger= LoggerFactory.getLogger(AttractionControllerTest.class);

    MockMvc mockMvc;


    @InjectMocks
    AttractionController AttractionController;

    @Mock
    AttractionService attractionService;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(AttractionController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))  // 한글 깨짐 처리
                .build();
    }



    @Test
    @DisplayName("관광지 조회")
    public void getAttractionList() throws Exception {
        SearchCondition condition=new SearchCondition(1,1,1,"title",false);
        given(attractionService.getAttractionList(condition,1))
                .willReturn(Arrays.asList(Attraction.builder()
                                                    .contentTypeId(1)
                                                    .contentId(1)
                                                    .firstImage("firstImage")
                                                    .title("title")
                                                    .latitude(1.0)
                                                    .longitude(1.0)
                                                    .overview("overview")
                                                    .gugunCode(1)
                                                    .sidoCode(1)
                                                    .addr1("addr1")
                                                    .build()));

        mockMvc.perform(get("/attraction?sidoCode=1&contentTypeCode=1&gugunCode=1&word=title"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("[{\"contentId\":1,\"contentTypeId\":1,\"title\":\"title\",\"addr1\":\"addr1\",\"firstImage\":\"firstImage\",\"sidoCode\":1,\"gugunCode\":1,\"latitude\":1.0,\"longitude\":1.0,\"overview\":\"overview\"}]"))
                .andDo(print());
    }
    @Test
    @DisplayName("특정 관광지 조회")
    public void getAttraction() throws Exception{
        given(attractionService.getAttraction(1))
                .willReturn(Attraction.builder()
                        .contentTypeId(1)
                        .contentId(1)
                        .firstImage("firstImage")
                        .title("title")
                        .latitude(1.0)
                        .longitude(1.0)
                        .overview("overview")
                        .gugunCode(1)
                        .sidoCode(1)
                        .addr1("addr1")
                        .build());

        mockMvc.perform(get("/attraction/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"contentId\":1,\"contentTypeId\":1,\"title\":\"title\",\"addr1\":\"addr1\",\"firstImage\":\"firstImage\",\"sidoCode\":1,\"gugunCode\":1,\"latitude\":1.0,\"longitude\":1.0,\"overview\":\"overview\"}"))
                .andDo(print());
    }


}
