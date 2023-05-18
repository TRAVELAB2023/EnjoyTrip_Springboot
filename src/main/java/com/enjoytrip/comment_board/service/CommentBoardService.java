package com.enjoytrip.comment_board.service;

import com.enjoytrip.comment_board.dto.ReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyUpdateDto;

public interface CommentBoardService {
    int registerReply(ReplyRegisterDto registerDto);
    int registerReplyReply(ReplyReplyRegisterDto replyReplyRegisterDto);
    void deleteReply(int replyId);
    void deleteReplyReply(int replyId);
    void updateReply(ReplyUpdateDto replyUpdateDto);
}
