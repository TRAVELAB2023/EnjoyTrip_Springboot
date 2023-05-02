package com.enjoytrip.members.service;

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
    void login() {
        Member member = Member.builder()
                .memberId(12)
                .email("admin@admin.com")
                .password("admin").build();
        try {
            Member returnMember = memberService.login(member);
            Assertions.assertEquals(member.getMemberId(),returnMember.getMemberId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void join() {
    }

    @Test
    void dropMember() {
    }

    @Test
    void modifyMemberPassword() {
    }

    @Test
    void isDuplicatedId() {
    }

    @Test
    void isDuplicatedEmail() {
    }
}