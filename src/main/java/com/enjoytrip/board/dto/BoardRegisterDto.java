package com.enjoytrip.board.dto;

import lombok.Getter;

@Getter
public class BoardRegisterDto {
    String title;
    int memberId;
    String content;

    public BoardRegisterDto(String title, int member_id, String content) {
        this.title = title;
        this.memberId = member_id;
        this.content = content;
    }
}
