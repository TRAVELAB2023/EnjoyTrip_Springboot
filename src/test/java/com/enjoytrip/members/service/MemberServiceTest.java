package com.enjoytrip.members.service;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.SQLException;

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
    void login() throws Exception {
        LoginDto loginDto = new LoginDto("admin@admin.com", "admin");

        SessionDto registerDto = memberService.login(loginDto);
        Assertions.assertEquals(12, registerDto.getMemberId());

    }

    @Test
    void join() throws Exception {
        RegisterDto registerDto = new RegisterDto("test@test.com", "test", true, "test");
        LoginDto loginDto = new LoginDto("test@test.com", "test");

        memberService.join(registerDto, "user");
        SessionDto sessionDto = memberService.login(loginDto);
        Assertions.assertEquals(sessionDto.getNickname(), "test");


    }

    @Test
    void dropMember() {
        int memberId = 12;
        try {
            memberService.dropMember(memberId);
            Assertions.assertEquals(memberService.findById(memberId).isDelYn(), true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void modifyMemberPassword() {
        int memberId = 12;
        try {
            memberService.modifyMemberPassword(memberId, "test");
            Assertions.assertEquals(memberService.findById(memberId).getPassword(), "test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void isDuplicatedEmail() {
        RegisterDto registerDto = new RegisterDto("test@test.com", "test", true, "test");
        try {
            memberService.join(registerDto, "user");
            Assertions.assertTrue(memberService.isDuplicatedEmail(registerDto.getEmail()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isDuplicatedNickname() {
        RegisterDto registerDto = new RegisterDto("test@test.com", "test", true, "test");
        try {
            memberService.join(registerDto, "user");
            Assertions.assertTrue(memberService.isDuplicatedNickname(registerDto.getNickname()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}