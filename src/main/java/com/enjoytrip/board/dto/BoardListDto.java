package com.enjoytrip.board.dto;

import com.enjoytrip.model.Board;

import java.time.LocalDateTime;

public class BoardListDto {
    int boardId;
    String title;
    int hit;
    String writerNickname;
    int writerId;
    LocalDateTime registerTime;

    public BoardListDto(Board board, String writerNickname) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.hit = boardId;
        this.writerId = board.getMemberId();
        this.writerNickname = writerNickname;
        this.registerTime = board.getRegisterTime();
    }
}
