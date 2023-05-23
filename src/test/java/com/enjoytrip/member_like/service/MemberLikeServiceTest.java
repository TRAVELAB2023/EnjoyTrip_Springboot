package com.enjoytrip.member_like.service;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberLikeServiceTest {

    @Autowired
    MemberLikeService memberLikeService;

    @Test
    void registerLike() {
        int memberId=12;
        MemberLikeDto memberLikeDto = new MemberLikeDto( 125266);

        try {
            memberLikeService.registerLike(memberLikeDto,memberId);

            assertTrue(memberLikeService.isCanFind(memberId,memberLikeDto.getAttractionId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteLike() {
        int memberId=12;
        MemberLikeDto memberLikeDto = new MemberLikeDto( 125266);
        try {
            memberLikeService.registerLike(memberLikeDto,memberId);
            assertTrue(memberLikeService.isCanFind(memberId,memberLikeDto.getAttractionId()));
            memberLikeService.registerLike(memberLikeDto,memberId);
            assertFalse(memberLikeService.isCanFind(memberId,memberLikeDto.getAttractionId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}