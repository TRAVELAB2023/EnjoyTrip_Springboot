package com.enjoytrip;


import com.enjoytrip.model.Gugun;
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

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@PropertySource(value = "classpath:application.properties")
public class SIdoRepoTest {

    private Logger logger= LoggerFactory.getLogger(SIdoRepoTest.class);

    @Autowired
    private SidoRepository sidoRepository;


    @Test
    @DisplayName("대한민국 도 조회")
    public void testGetSidoList(){
        List<Sido> list=sidoRepository.findAll();
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),17);

    }
    @Test
    @DisplayName("대한민국 군구 조회")
    public void testGetGugunList(){
        List<Gugun> list=sidoRepository.getGugunList(1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),25);
    }
}
