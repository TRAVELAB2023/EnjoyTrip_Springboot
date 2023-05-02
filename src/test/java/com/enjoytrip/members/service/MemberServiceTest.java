package com.enjoytrip.members.service;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.MemberDto;
import com.enjoytrip.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void findById() {
        try {
            Member member = memberService.findById(12);
            Assertions.assertEquals(member.getNickname(), "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void login() {
        LoginDto loginDto = new LoginDto("admin@admin.com","admin");
        try {
            MemberDto memberDto = memberService.login(loginDto);
            Assertions.assertEquals(12, memberDto.getMemberId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void join() {
        Member member = Member.builder()
                .nickname("test")
                .password("test")
                .marketingAgreement(true)
                .role("user")
                .email("test@test.com").build();
        LoginDto loginDto = new LoginDto("test@test.com","test");
        try {
            memberService.join(member);
            MemberDto memberDto = memberService.login(loginDto);
            Assertions.assertEquals(member.getNickname(), memberDto.getNickname());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void dropMember() {
        int memberId = 12;
        try {
            memberService.dropMember(memberId);
            Assertions.assertEquals(memberService.findById(memberId).isDelYn(),true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void modifyMemberPassword() {
        int memberId = 12;
        try {
            memberService.modifyMemberPassword(memberId,"test");
            Assertions.assertEquals(memberService.findById(memberId).getPassword(),"test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    void isDuplicatedEmail() {
        Member member = Member.builder()
                .nickname("test")
                .password("test")
                .marketingAgreement(true)
                .role("user")
                .email("test@test.com").build();
        try {
            memberService.join(member);
            Assertions.assertTrue(memberService.isDuplicatedEmail(member.getEmail()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isDuplicatedNickname() {
        Member member = Member.builder()
                .nickname("test")
                .password("test")
                .marketingAgreement(true)
                .role("user")
                .email("test@test.com").build();
        try {
            memberService.join(member);
            Assertions.assertTrue(memberService.isDuplicatedNickname(member.getNickname()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}