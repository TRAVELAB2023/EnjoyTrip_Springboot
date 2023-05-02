package com.enjoytrip.members.dto;


import lombok.Getter;

@Getter
public class LoginDto {

    String email;
    String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
