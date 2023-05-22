package com.enjoytrip.board.dto;

import com.enjoytrip.model.Board;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListDto {


    int boardId;
    String title;
    int hit;
    String writerNickname;
    int writerId;

    @JsonDeserialize
    @JsonSerialize
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
