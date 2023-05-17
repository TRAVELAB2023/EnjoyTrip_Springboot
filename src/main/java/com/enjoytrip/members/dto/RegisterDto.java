package com.enjoytrip.members.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class RegisterDto {

    @Email
    private String email;

    @NotBlank
    private String nickname;
    private boolean marketing;
    private String role;
    @NotBlank
    private String password;



    public RegisterDto(String email, String nickname, boolean marketing, String password) {
        this.email = email;
        this.nickname = nickname;
        this.marketing = marketing;
        this.role = "user";
        this.password=password;
    }

    public void grantAdmin() {
        this.role = "admin";
    }
    public void revokeAdmin(){
        this.role = "user";
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", marketing=" + marketing +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
