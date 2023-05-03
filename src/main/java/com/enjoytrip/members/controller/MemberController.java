package com.enjoytrip.members.controller;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController

public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam Map<String, String> map){
        boolean marketingAgreement = map.get("marketing").equals("true");
        RegisterDto registerDto = new RegisterDto(map.get("email"),map.get("nickname"),marketingAgreement, map.get("password"));
        try {
            memberService.join(registerDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<SessionDto> login(@RequestParam Map<String, String> map){
        LoginDto loginDto = new LoginDto(map.get("email"),map.get("password"));
        try {
            SessionDto sessionDto = memberService.login(loginDto);
            return new ResponseEntity<SessionDto>(sessionDto, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}