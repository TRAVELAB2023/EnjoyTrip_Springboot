package com.enjoytrip.Attraction;


import com.enjoytrip.attraction.repository.AttractionRepository;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.Gugun;
import com.enjoytrip.model.SearchCondition;
import com.enjoytrip.model.Sido;
import com.enjoytrip.sido.repository.SidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@PropertySource(value = "classpath:application.properties")
public class AttractionRepoTest {

    private Logger logger= LoggerFactory.getLogger(AttractionRepoTest.class);

    @Autowired
    private AttractionRepository attractionRepository;


    @Test
    @DisplayName("전체 관광지 조회")
    public void testGetAttractionList(){
        SearchCondition condition=new SearchCondition(0,0,0,null);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),200);

    }
    @Test
    @DisplayName("특정 도 관광지 조회(결과가 있을 때)")
    public void testGetSidoAttractionList(){
        SearchCondition condition=new SearchCondition(1,1,0,null);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),200);

    }

    @Test
    @DisplayName("특정 도 관광지 조회(결과가 없을 때)")
    public void testGetSidoAttractionListEmpty(){
        SearchCondition condition=new SearchCondition(2141,0,1,null);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),0);

    }

    @Test
    @DisplayName("특정 시군구 관광지 조회(결과가 있을 때)")
    public void testGetGugunAttractionList(){
        SearchCondition condition=new SearchCondition(1,2,0,null);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),86);

    }
    @Test
    @DisplayName("특정 시군구 관광지 조회(결과가 없을 때)")
    public void testGetGugunAttractionListEmpty(){
        SearchCondition condition=new SearchCondition(1,200,0,null);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),0);

    }

    @Test
    @DisplayName("특정 시군구와 단어가 포함된 관광지 조회(결과가 있을 때)")
    public void testGetGugunWordAttractionList(){
        SearchCondition condition=new SearchCondition(1,2,0,"호텔");
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),1);

    }

}
