package com.enjoytrip.member_like;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.member_like.service.MemberLikeService;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberLikeControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper;
    @Autowired
    MemberLikeService memberLikeService;

    @Autowired
    private MemberRepository memberRepository;
    MockHttpSession mockHttpSession;
    Member member;
    @BeforeEach
    public void init(){
        objectMapper=new ObjectMapper();
        mockHttpSession=new MockHttpSession();
        member=memberRepository.findMemberByEmailAndPasswordAndDelYn("admin@admin.com","admin",false);
        SessionDto sessionDto=new SessionDto(member);
        mockHttpSession.setAttribute("userInfo",sessionDto);
    }
    @Test
    void like() throws Exception {
        MemberLikeDto memberLikeDto = new MemberLikeDto(125266);

        mockMvc.perform(post("/member-like")
                .session(mockHttpSession)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberLikeDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));


    }

    @Test
    void notLike() throws Exception {
        MemberLikeDto memberLikeDto = new MemberLikeDto(125266);
        memberLikeService.registerLike(memberLikeDto,member.getMemberId());

        mockMvc.perform(post("/member-like")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberLikeDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }
}