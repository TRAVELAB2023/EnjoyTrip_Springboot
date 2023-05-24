package com.enjoytrip.members.controller;

import com.enjoytrip.auth.service.JwtService;
import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.dto.SessionReceiveDto;
import com.enjoytrip.members.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/auth")

public class MemberAuthController {
    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final MemberService memberService;
    private final JwtService jwtService;

    public MemberAuthController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterDto registerDto) throws SQLException {
        memberService.join(registerDto, "user");
        return new ResponseEntity(HttpStatus.OK);

    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody SessionReceiveDto sessionDto, HttpServletRequest request)
            throws Exception {
        System.out.println("------------------------------------------------------------");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
        if (jwtService.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(sessionDto.getEmail()))) {
                String accessToken = jwtService.createAccessToken("userid", sessionDto.getMemberId());
                resultMap.put("auth-token", accessToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDto loginDto) throws Exception {
        System.out.println(loginDto);
        Map<String, Object> resultMap = new HashMap<>();
        SessionDto loginUser = memberService.login(loginDto);
        String accessToken = jwtService.createAccessToken("userid", loginUser.getMemberId());// key, data
        String refreshToken = jwtService.createRefreshToken("userid", loginUser.getMemberId());// key, data
        memberService.saveRefreshToken(loginDto.getEmail(), refreshToken);
        resultMap.put("auth-token", accessToken);
        resultMap.put("refresh-token", refreshToken);
        resultMap.put("message", SUCCESS);


        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }

    @GetMapping("/check-duplicate-email/{email}")
    public ResponseEntity<String> isDuplicatedEmail(@PathVariable(name = "email") String email) throws SQLException {
        if (memberService.isDuplicatedEmail(email)) {
            return new ResponseEntity("중복", HttpStatus.OK);
        }
        if (!isValidEmail(email)) {
            return new ResponseEntity<>("잘못된 형식", HttpStatus.OK);
        }
        return new ResponseEntity("사용가능", HttpStatus.OK);
    }
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    @GetMapping("/check-duplicate-nickname/{nickname}")
    public ResponseEntity<String> isDuplicatedNickname(@PathVariable(name = "nickname") String nickname) throws SQLException {
        if (memberService.isDuplicatedNickname(nickname)) {
            return new ResponseEntity("중복", HttpStatus.OK);
        }
        return new ResponseEntity("사용가능", HttpStatus.OK);

    }
    @GetMapping("/info/{memberId}")
    public ResponseEntity<Map<String, Object>> getInfo(@PathVariable(name = "memberId") int memberId,
                                                       HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        if (jwtService.checkToken(request.getHeader("auth-token"))) {
            try {
                SessionDto memberDto = memberService.info(memberId);
                resultMap.put("userInfo", memberDto);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
