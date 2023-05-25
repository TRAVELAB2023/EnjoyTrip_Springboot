package com.enjoytrip.members.controller;


import com.enjoytrip.auth.service.JwtService;
import com.enjoytrip.common.LoginMember;
import com.enjoytrip.members.dto.ModifyPasswordDto;
import com.enjoytrip.members.dto.PasswordDto;
import com.enjoytrip.members.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String HEADER_AUTH = "auth-token";
    private final JwtService jwtService;

    public MemberController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @PostMapping("/logout/{email}")
    public ResponseEntity logout(@PathVariable(name = "email") String email) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        memberService.deleteRefreshToken(email);
        resultMap.put("message", SUCCESS);
        return new ResponseEntity<>(resultMap, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/drop-member")
    public ResponseEntity dropMember() throws SQLException {
        int memberId = Integer.parseInt(jwtService.getUserId());
        memberService.dropMember(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/password-check")
    public ResponseEntity checkPassword(@RequestBody PasswordDto passwordDto, @LoginMember int memberId) throws Exception {
        memberService.isRightPassword(passwordDto.getPassword(), memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity modifyPassword(@Valid @RequestBody ModifyPasswordDto modifyPasswordDto, @LoginMember int memberId) throws SQLException {
        memberService.modifyMemberPassword(memberId, modifyPasswordDto);
        return new ResponseEntity(HttpStatus.OK);
    }


}
