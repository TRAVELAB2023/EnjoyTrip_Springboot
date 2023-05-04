package com.enjoytrip.members.controller;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Map;

@RestController

public class MemberAuthController {

    private final MemberService memberService;

    public MemberAuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterDto registerDto) throws SQLException {

        memberService.join(registerDto);
        return new ResponseEntity(HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, HttpSession session) throws Exception {

        SessionDto sessionDto = memberService.login(loginDto);
        session.setAttribute("userinfo", sessionDto);
        return new ResponseEntity(HttpStatus.OK);

    }


}
