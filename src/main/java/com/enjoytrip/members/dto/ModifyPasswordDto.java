package com.enjoytrip.members.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ModifyPasswordDto {

    @NotBlank(message="값이 입력되어 있지 않습니다.")
    private String curPassword;
    @NotBlank(message="값이 입력되어 있지 않습니다.")
    private String newPassword;
}
