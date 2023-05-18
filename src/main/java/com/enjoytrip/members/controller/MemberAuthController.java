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

        memberService.join(registerDto, "user");
        return new ResponseEntity(HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto, HttpSession session) throws Exception {

        SessionDto sessionDto = memberService.login(loginDto);
        session.setAttribute("userinfo", sessionDto);
        return new ResponseEntity(HttpStatus.OK);

    }
    @GetMapping("/check-duplicate-email/{email}")
    public ResponseEntity<String> isDuplicatedEmail(@PathVariable(name = "email") String email) throws SQLException {
        if (memberService.isDuplicatedEmail(email)) {
            return new ResponseEntity("중복", HttpStatus.OK);
        }
        return new ResponseEntity("사용가능", HttpStatus.OK);

    }

    @GetMapping("/check-duplicate-nickname/{nickname}")
    public ResponseEntity<String> isDuplicatedNickname(@PathVariable(name = "nickname") String nickname) throws SQLException {
        if (memberService.isDuplicatedNickname(nickname)) {
            return new ResponseEntity("중복", HttpStatus.OK);
        }
        return new ResponseEntity("사용가능", HttpStatus.OK);

    }

}
