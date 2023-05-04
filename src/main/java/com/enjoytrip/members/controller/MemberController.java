package com.enjoytrip.members.controller;


import com.enjoytrip.members.dto.RegisterDto;
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
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }
}
