package com.enjoytrip.email.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
public class EmailRequestDto {
    @Email(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",
            message = "이메일 양식이 맞지 않습니다.")
    private String email;
}
