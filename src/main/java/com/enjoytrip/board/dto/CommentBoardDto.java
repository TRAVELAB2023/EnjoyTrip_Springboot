package com.enjoytrip.board.dto;

import com.enjoytrip.model.CommentBoard;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class CommentBoardDto {
    int commentId;
    String content;
    int memberId;
    String memberNickname;
    int rgroup;
    boolean replyDepth;

    public CommentBoardDto(CommentBoard commentBoard, String memberNickname) {
        this.commentId = commentBoard.getCommentId();
        this.content = commentBoard.getContent();
        this.memberId = commentBoard.getMemberId();
        this.memberNickname = memberNickname;
        this.rgroup = commentBoard.getRgroup();
        this.replyDepth = commentBoard.isReplyDepth();
    }

    public CommentBoardDto(int commentId, String content, int memberId, String memberNickname, int rgroup, boolean replyDepth) {
        this.commentId = commentId;
        this.content = content;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.rgroup = rgroup;
        this.replyDepth = replyDepth;
    }

    @Override
    public String toString() {
        return "CommentBoardDto{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", memberId=" + memberId +
                ", memberNickname='" + memberNickname + '\'' +
                ", rgruop=" + rgroup +
                ", replyDepth=" + replyDepth +
                '}';
    }
}
