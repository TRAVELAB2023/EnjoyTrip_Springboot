package com.enjoytrip.members.controller;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

@RestController

public class MemberAuthController {

    private final MemberService memberService;

    public MemberAuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity register(@RequestParam Map<String, String> map) {
        boolean marketingAgreement = map.get("marketing").equals("true");
        RegisterDto registerDto = new RegisterDto(map.get("email"), map.get("nickname"), marketingAgreement, map.get("password"));
        try {
            memberService.join(registerDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestParam Map<String, String> map, HttpSession session) {
        LoginDto loginDto = null;
        try {
            loginDto = new LoginDto(map.get("email"), map.get("password"));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            SessionDto sessionDto = memberService.login(loginDto);
            session.setAttribute("userinfo", sessionDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }




}
