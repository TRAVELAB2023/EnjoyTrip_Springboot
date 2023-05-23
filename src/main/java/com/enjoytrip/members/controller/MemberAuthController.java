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
                logger.debug("token : {}, memberDto : {}", token, sessionDto);
        if (jwtService.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(sessionDto.getEmail()))) {
                String accessToken = jwtService.createAccessToken("userid", sessionDto.getMemberId());
                logger.debug("token : {}", accessToken);
                logger.debug("정상적으로 액세스토큰 재발급!!!");
                resultMap.put("auth-token", accessToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
        } else {
            logger.debug("리프레쉬토큰도 사용불!!!!!!!");
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
        logger.debug("로그인 accessToken 정보 : {}", accessToken);
        logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
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
        return new ResponseEntity("사용가능", HttpStatus.OK);

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
//		logger.debug("userid : {} ", userid);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        if (jwtService.checkToken(request.getHeader("auth-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                SessionDto memberDto = memberService.info(memberId);
                resultMap.put("userInfo", memberDto);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!"+ request.getHeader("auth-token"));
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
