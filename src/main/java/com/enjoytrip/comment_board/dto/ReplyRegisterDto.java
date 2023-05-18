package com.enjoytrip.comment_board.dto;

import lombok.Getter;

@Getter
public class ReplyRegisterDto {
    String content;
    int memberId;
    int boardId;

    public ReplyRegisterDto(String content, int memberId, int boardId) {
        this.content = content;
        this.memberId = memberId;
        this.boardId = boardId;
    }
}
