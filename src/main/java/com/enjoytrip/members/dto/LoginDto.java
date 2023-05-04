package com.enjoytrip.members.dto;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {

    @Email
    private String email;
    @NotBlank
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
