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
    public void getAttractionList(){
        String url = "http://localhost:"+port+"/attraction?sidoCode=1&gugunCode=1&word=호텔";
        ResponseEntity<List<Attraction>> entity=restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<List<Attraction>>() {});
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody().size()).isEqualTo("15");
    }

    @Test
    @DisplayName("특정 관광지 조회")
    public void getAttraction(){
        String url = "http://localhost:"+port+"/attraction/125266";
        ResponseEntity<Attraction> entity=restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<Attraction>() {});
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody().toString()).isEqualTo("Attraction{contentId=125266, contentTypeId=12, title='국립 청태산자연휴양림', addr1='강원도 횡성군 둔내면 청태산로 610', firstImage='http://tong.visitkorea.or.kr/cms/resource/21/2657021_image2_1.jpg', sidoCode=32, gugunCode=18, latitude=37.52251412, longitude=128.2919115, overview='해발 1,200m의 청태산을 주봉으로 하여 인공림과 천연림이 잘 조화된 울창한 산림을 바탕으로 한 국유림 경영 시범단지로서 숲속에는 온갖 야생 동식물이 고루 서식하고 있어 자연박물관을 찾은 기분을 느낄 수 있다. 영동고속도로 신갈기점 강릉방향 128km 지점에 위치하고 있어 여름철 동해안 피서객들이 잠시 쉬었다 가기에 편리하고, 청소년의 심신수련을 위한 숲속교실도 설치되어 있으며 울창한 잣나무 숲속의 산림욕장은 한번왔다간 사람은 누구나 매료되어 찾는 곳이기도 하다. * 구역면적 - 403 ha'}");
    }
    @Test
    @DisplayName("존재하지 않는 특정 관광지 조회")
    public void getAttractionException(){
        String url = "http://localhost:"+port+"/attraction/1";
        ResponseEntity<String> entity=restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,new ParameterizedTypeReference<String>() {});
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(entity.getBody().toString()).isEqualTo("관광지가 존재하지 않습니다.");
    }







}
