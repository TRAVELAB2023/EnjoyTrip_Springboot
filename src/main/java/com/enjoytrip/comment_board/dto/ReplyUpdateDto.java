package com.enjoytrip.comment_board.dto;

import lombok.Getter;

@Getter
public class ReplyUpdateDto {
    int replyId;
    String content;

    public ReplyUpdateDto(int replyId, String content) {
        this.replyId = replyId;
        this.content = content;
    }
}
