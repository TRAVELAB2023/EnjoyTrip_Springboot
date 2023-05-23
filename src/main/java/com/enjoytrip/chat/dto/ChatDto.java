package com.enjoytrip.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ChatDto {
    @Size(min=1,max = 10000, message = "최소 길이 5글자, 최대길이 30글자 이내로 질문해주세요")
    private String message;
}
