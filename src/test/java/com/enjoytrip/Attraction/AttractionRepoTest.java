package com.enjoytrip.Attraction;


import com.enjoytrip.attraction.repository.AttractionRepository;
import com.enjoytrip.member_like.repository.MemberLikeRepository;
import com.enjoytrip.model.*;
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
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@PropertySource(value = "classpath:application.properties")
public class AttractionRepoTest {

    private Logger logger= LoggerFactory.getLogger(AttractionRepoTest.class);

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private MemberLikeRepository memberLikeRepository;
    @Test
    @DisplayName("전체 관광지 조회")
    public void testGetAttractionList(){
        SearchCondition condition=new SearchCondition(0,0,0,null,false);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),200);

    }
    @Test
    @DisplayName("특정 도 관광지 조회(결과가 있을 때)")
    public void testGetSidoAttractionList(){
        SearchCondition condition=new SearchCondition(1,1,0,null,false);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),200);

    }

    @Test
    @DisplayName("특정 도 관광지 조회(결과가 없을 때)")
    public void testGetSidoAttractionListEmpty(){
        SearchCondition condition=new SearchCondition(2141,0,1,null,false);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),0);

    }

    @Test
    @DisplayName("특정 시군구 관광지 조회(결과가 있을 때)")
    public void testGetGugunAttractionList(){
        SearchCondition condition=new SearchCondition(1,2,0,null,false);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),86);

    }
    @Test
    @DisplayName("특정 시군구 관광지 조회(결과가 없을 때)")
    public void testGetGugunAttractionListEmpty(){
        SearchCondition condition=new SearchCondition(1,200,0,null,false);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),0);

    }

    @Test
    @DisplayName("특정 시군구와 단어가 포함된 관광지 조회(결과가 있을 때)")
    public void testGetGugunWordAttractionList(){
        SearchCondition condition=new SearchCondition(1,2,0,"호텔",false);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,1);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(list.size(),1);

    }


    @Test
    @DisplayName("내가 좋아요한 관광지 조회")
    @Rollback
    public void testGetMemberLikeAttractionList(){
        int memberId=12;
        MemberLike memberLike=MemberLike.builder().memberId(memberId).attractionId(125266).build();
        memberLikeRepository.save(memberLike);
        SearchCondition condition=new SearchCondition(0,0,0,null,true);
        List<Attraction> list=attractionRepository.findBySearchCondtion(condition,memberId);
        logger.info("데이터 : {}",list);
        Assertions.assertEquals(1,list.size());
    }

}
