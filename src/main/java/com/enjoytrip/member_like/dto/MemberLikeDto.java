package com.enjoytrip.member_like.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class MemberLikeDto {
    int memberId;
    int attractionId;

    public MemberLikeDto(int memberId, int attractionId) {
        this.memberId = memberId;
        this.attractionId = attractionId;
    }
}
