package com.enjoytrip.sido;


import com.enjoytrip.model.Sido;
import com.enjoytrip.sido.dto.GugunDto;
import com.enjoytrip.sido.dto.SidoDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SidoIntegretionTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("대한민국 도 조회")
    public void getSido(){
        String url = "http://localhost:"+port+"/sido";
        ResponseEntity<List<SidoDto>> entity=restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<List<SidoDto>>() {});
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody().size()).isEqualTo(17);
    }

    @Test
    @DisplayName("대한민국 군구 조회")
    public void getGugunList(){
        String url = "http://localhost:"+port+"/gugun/1";
        ResponseEntity<List<GugunDto>> entity=restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<List<GugunDto>>() {});
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody().size()).isEqualTo(25);
    }



}
