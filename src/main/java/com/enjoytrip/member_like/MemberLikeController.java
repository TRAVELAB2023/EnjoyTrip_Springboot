package com.enjoytrip.member_like;

import com.enjoytrip.common.LoginMember;
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
    public ResponseEntity<Integer> like(@LoginMember int memberId, @RequestBody MemberLikeDto memberLikeDto) throws SQLException {
        Integer result=memberLikeService.registerLike(memberLikeDto,memberId);

        return new ResponseEntity(result,HttpStatus.OK);
    }
    @GetMapping("/{attractionId}")
    public ResponseEntity<Boolean> like(@LoginMember int memberId, @PathVariable int attractionId) throws SQLException {

        boolean result=memberLikeService.isCanFind(memberId,attractionId);

        return new ResponseEntity(result,HttpStatus.OK);
    }

}
