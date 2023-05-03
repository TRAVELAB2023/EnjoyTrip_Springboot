package com.enjoytrip.members.dto;

import com.enjoytrip.model.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SessionDto {
    private int memberId;
    private String email;
    private String nickname;
    private LocalDateTime registerTime;
    private boolean marketingAgreement;
    private String role;

    public SessionDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.registerTime = member.getRegisterTime();
        this.marketingAgreement = member.isMarketingAgreement();
        this.role = member.getRole();
    }
}
