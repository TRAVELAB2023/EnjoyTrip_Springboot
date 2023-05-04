package com.enjoytrip.members.controller;


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
    @ResponseBody
    public ResponseEntity logout(HttpSession session) {
        if(session.getAttribute("userinfo")==null){
         throw new IllegalArgumentException();
        }
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/dropMember")
    @ResponseBody
    public ResponseEntity dropMember(HttpSession session) throws SQLException {
        SessionDto sessionDto = (SessionDto) session.getAttribute("userinfo");

        if (sessionDto == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

            memberService.dropMember(sessionDto.getMemberId());
            return new ResponseEntity(HttpStatus.OK);

    }
}
