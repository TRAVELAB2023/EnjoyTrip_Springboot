package com.enjoytrip.members.repository;

import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.mapper.MemberIdMapping;
import com.enjoytrip.members.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findMemberIdByNickname() throws SQLException {
        List<RegisterDto> registerDtoList = new ArrayList<>();
        registerDtoList.add(new RegisterDto("11@22","12",true,"11"));
        registerDtoList.add(new RegisterDto("121@22","121",true,"11"));
        registerDtoList.add(new RegisterDto("131@22","131",true,"11"));
        registerDtoList.add(new RegisterDto("113@22","113",true,"11"));
        registerDtoList.add(new RegisterDto("111@22","111",true,"11"));
        for (RegisterDto registerDto : registerDtoList) {
            memberService.join(registerDto);
        }

        List<MemberIdMapping> list = memberRepository.findAllIdByNicknameContains("11");
        Assertions.assertEquals(list.size(),2);

    }
}