package com.enjoytrip.board.dto;

import com.enjoytrip.model.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardDto {
    int boardId;
    String title;
    int hit;
    String content;
    String writerNickname;
    LocalDateTime registerTime;

    int writerId;
    private List<CommentBoardDto> commentBoardList;
    private List<ImageDto> imageList;

    public BoardDto(Board board, List<CommentBoardDto> commentBoards, List<ImageDto> images, String memberNickname) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.hit = board.getHit();
        this.content = board.getContent();
        this.writerId = board.getMemberId();
        this.commentBoardList = commentBoards;
        this.imageList = images;
        this.writerNickname = memberNickname;
        this.registerTime = board.getRegisterTime();
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", hit=" + hit +
                ", content='" + content + '\'' +
                ", writerNickname='" + writerNickname + '\'' +
                ", writerId=" + writerId +
                ", commentBoardList=" + commentBoardList +
                ", imageList=" + imageList +
                '}';
    }
}
