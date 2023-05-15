package com.enjoytrip.member_like;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.member_like.service.MemberLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/member-like")
public class MemberLikeController {
    private final MemberLikeService memberLikeService;
    public MemberLikeController(MemberLikeService memberLikeService) {
        this.memberLikeService = memberLikeService;
    }

    @PostMapping("/like/{member-id}/{attraction-id}")
    public ResponseEntity like(@PathVariable(name = "member-id") int memberId, @PathVariable(name = "attraction-id") int attractionId) throws SQLException {
        MemberLikeDto memberLikeDto = new MemberLikeDto(memberId, attractionId);
        memberLikeService.registerLike(memberLikeDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/not-like/{member-id}/{attraction-id}")
    public ResponseEntity notLike(@PathVariable(name = "member-id") int memberId, @PathVariable(name = "attraction-id") int attractionId) throws SQLException {
        MemberLikeDto memberLikeDto = new MemberLikeDto(memberId, attractionId);
        memberLikeService.deleteLike(memberLikeDto);

        return new ResponseEntity(HttpStatus.OK);
    }

}
