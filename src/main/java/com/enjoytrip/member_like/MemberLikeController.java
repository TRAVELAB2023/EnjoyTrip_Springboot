package com.enjoytrip.member_like;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.member_like.service.MemberLikeService;
import com.enjoytrip.members.dto.SessionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController
@RequestMapping("/member-like")
public class MemberLikeController {
    private final MemberLikeService memberLikeService;
    public MemberLikeController(MemberLikeService memberLikeService) {
        this.memberLikeService = memberLikeService;
    }

    @PostMapping("")
    public ResponseEntity<Integer> like(HttpSession session, @RequestBody MemberLikeDto memberLikeDto) throws SQLException {
        SessionDto sessionDto= (SessionDto) session.getAttribute("userInfo");
        Integer result=memberLikeService.registerLike(memberLikeDto,12);

        return new ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping("/{attractionId}")
    public ResponseEntity<Boolean> like(HttpSession session, @PathVariable int attractionId) throws SQLException {

        SessionDto sessionDto= (SessionDto) session.getAttribute("userInfo");
        boolean result=memberLikeService.isCanFind(12,attractionId);

        return new ResponseEntity(result,HttpStatus.OK);
    }

}
