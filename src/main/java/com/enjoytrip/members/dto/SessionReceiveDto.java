package com.enjoytrip.members.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SessionReceiveDto {
    int memberId;
    String email;
    String nickname;
    @JsonSerialize
    @JsonDeserialize
    LocalDateTime registerTime;
    boolean marketingAgreement;
    String role;
    public SessionReceiveDto() {

    }

    public SessionReceiveDto(int memberId, String email, String nickname, LocalDateTime registerTime, boolean marketingAgreement, String role) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.registerTime = registerTime;
        this.marketingAgreement = marketingAgreement;
        this.role = role;
    }
}
