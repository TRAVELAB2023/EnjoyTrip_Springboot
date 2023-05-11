package com.enjoytrip.member_like;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.member_like.service.MemberLikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberLikeControllerTest {
    MemberLikeDto memberLikeDto = new MemberLikeDto(12, 125266);

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberLikeService memberLikeService;

    @Test
    void like() throws Exception {

        mockMvc.perform(post("/member-like/like/" + memberLikeDto.getMemberId() + "/" + memberLikeDto.getAttractionId() + "")
        ).andExpect(status().isOk());

        assertTrue(memberLikeService.isCanFind(memberLikeDto.getMemberId(), memberLikeDto.getAttractionId()));


    }

    @Test
    void notLike() throws Exception {
        mockMvc.perform(post("/member-like/like/" + memberLikeDto.getMemberId() + "/" + memberLikeDto.getAttractionId() + "")
        ).andExpect(status().isOk());
        mockMvc.perform(post("/member-like/not-like/" + memberLikeDto.getMemberId() + "/" + memberLikeDto.getAttractionId() + "")
        ).andExpect(status().isOk());
        assertFalse(memberLikeService.isCanFind(memberLikeDto.getMemberId(), memberLikeDto.getAttractionId()));

    }
}