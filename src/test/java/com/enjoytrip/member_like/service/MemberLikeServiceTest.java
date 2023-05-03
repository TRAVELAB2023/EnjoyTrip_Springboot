package com.enjoytrip.member_like.service;

import com.enjoytrip.model.MemberLike;
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
        MemberLike memberLike = MemberLike.builder().
                memberId(12)
                .attractionId(125266).
                build();
        try {
            memberLikeService.registerLike(memberLike);

            assertTrue(memberLikeService.isCanFind(memberLike.getMemberId(),memberLike.getAttractionId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteLike() {
        MemberLike memberLike = MemberLike.builder().
                memberId(12)
                .attractionId(125266).
                build();
        try {
            memberLikeService.registerLike(memberLike);
            assertTrue(memberLikeService.isCanFind(memberLike.getMemberId(),memberLike.getAttractionId()));
            memberLikeService.deleteLike(memberLike.getMemberId(),memberLike.getAttractionId());
            assertFalse(memberLikeService.isCanFind(memberLike.getMemberId(),memberLike.getAttractionId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}