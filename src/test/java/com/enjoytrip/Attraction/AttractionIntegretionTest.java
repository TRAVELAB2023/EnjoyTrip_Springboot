package com.enjoytrip.Attraction;


import com.enjoytrip.model.Attraction;
import com.enjoytrip.sido.dto.SidoDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttractionIntegretionTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("관광지 조회")
    public void getAttraction(){
        String url = "http://localhost:"+port+"/attraction?sidoCode=1&gugunCode=1&word=호텔";
        ResponseEntity<List<Attraction>> entity=restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<List<Attraction>>() {});
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody().size()).isEqualTo(15);
    }





}
