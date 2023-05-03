package com.enjoytrip.members.dto;

import com.enjoytrip.model.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegisterDto {
    private String email;
    private String nickname;
    private boolean marketingAgreement;
    private String role;
    private String password;



    public RegisterDto(String email, String nickname, boolean marketingAgreement, String password) {
        this.email = email;
        this.nickname = nickname;
        this.marketingAgreement = marketingAgreement;
        this.role = "user";
        this.password=password;
    }
}
