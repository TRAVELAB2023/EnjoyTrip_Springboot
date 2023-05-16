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
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        if (session.getAttribute("userinfo") == null) {
            throw new IllegalArgumentException();
        }
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/drop-member")
    public ResponseEntity dropMember(HttpSession session) throws SQLException {
        SessionDto sessionDto = (SessionDto) session.getAttribute("userinfo");

        memberService.dropMember(sessionDto.getMemberId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/password-check")
    public ResponseEntity checkPassword(@RequestBody LoginDto loginDto) throws Exception {
        if (memberService.isWritePassword(loginDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    @PatchMapping("/modify-password")
    public ResponseEntity modifyPassword(@RequestParam("newPassword") String password, HttpSession session) throws SQLException {
        SessionDto sessionDto = (SessionDto) session.getAttribute("userinfo");
        memberService.modifyMemberPassword(sessionDto.getMemberId(),password);
        return new ResponseEntity(HttpStatus.OK);
    }


}
