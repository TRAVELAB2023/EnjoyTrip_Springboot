package com.enjoytrip.board.dto;

import com.enjoytrip.model.CommentBoard;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class CommentBoardDto {
    int commentId;
    String content;
    int memberId;
    String memberNickname;
    int rgroup;
    boolean replyDepth;

    @JsonSerialize
    LocalDateTime registerTime;
    public CommentBoardDto(CommentBoard commentBoard, String memberNickname) {
        this.commentId = commentBoard.getCommentId();
        this.content = commentBoard.getContent();
        this.memberId = commentBoard.getMemberId();
        this.memberNickname = memberNickname;
        this.rgroup = commentBoard.getRgroup();
        this.replyDepth = commentBoard.isReplyDepth();
        this.registerTime=commentBoard.getRegisterTime();
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
