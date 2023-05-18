package com.enjoytrip.comment_board.dto;

import lombok.Getter;

@Getter
public class ReplyReplyRegisterDto extends ReplyRegisterDto{
    int rgroup;

    public ReplyReplyRegisterDto(String content, int memberId, int boardId, int rgroup) {
        super(content, memberId, boardId);
        this.rgroup = rgroup;
    }
}
