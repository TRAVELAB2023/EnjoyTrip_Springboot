package com.enjoytrip.members.dto;

import com.enjoytrip.model.Member;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SessionDto {
     int memberId;
     String email;
     String nickname;
    @JsonSerialize
    @JsonDeserialize
     LocalDateTime registerTime;
     boolean marketingAgreement;
     String role;

    public SessionDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.registerTime = member.getRegisterTime();
        this.marketingAgreement = member.isMarketingAgreement();
        this.role = member.getRole();
    }


    @Override
    public String toString() {
        return "SessionDto{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", registerTime=" + registerTime +
                ", marketingAgreement=" + marketingAgreement +
                ", role='" + role + '\'' +
                '}';
    }
}
