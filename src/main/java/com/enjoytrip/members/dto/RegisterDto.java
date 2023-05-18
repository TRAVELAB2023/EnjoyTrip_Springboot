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
    @NotBlank
    private String password;



    public RegisterDto(String email, String nickname, boolean marketing, String password) {
        this.email = email;
        this.nickname = nickname;
        this.marketing = marketing;
        this.password=password;
    }


    @Override
    public String toString() {
        return "RegisterDto{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", marketing=" + marketing +
                ", password='" + password + '\'' +
                '}';
    }
}
