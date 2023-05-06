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
        MemberLikeDto memberLikeDto = new MemberLikeDto(12, 125266);

        try {
            memberLikeService.registerLike(memberLikeDto);

            assertTrue(memberLikeService.isCanFind(memberLikeDto.getMemberId(),memberLikeDto.getAttractionId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteLike() {
        MemberLikeDto memberLikeDto = new MemberLikeDto(12, 125266);
        try {
            memberLikeService.registerLike(memberLikeDto);
            assertTrue(memberLikeService.isCanFind(memberLikeDto.getMemberId(),memberLikeDto.getAttractionId()));
            memberLikeService.deleteLike(memberLikeDto);
            assertFalse(memberLikeService.isCanFind(memberLikeDto.getMemberId(),memberLikeDto.getAttractionId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}