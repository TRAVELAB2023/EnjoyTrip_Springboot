package com.enjoytrip.members.dto;

import com.enjoytrip.model.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDto {
    int memberId;
    String email;
    String nickname;
    LocalDateTime registerTime;
    boolean marketingAgreement;
    String role;

    public MemberDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.registerTime = member.getRegisterTime();
        this.marketingAgreement = member.isMarketingAgreement();
        this.role = member.getRole();
    }
}
