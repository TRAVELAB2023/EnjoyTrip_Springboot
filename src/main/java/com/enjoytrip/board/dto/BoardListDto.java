package com.enjoytrip.board.dto;

import com.enjoytrip.model.Board;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class BoardListDto {


    int boardId;
    String title;
    int hit;
    String writerNickname;
    int writerId;

    String registerTime;

    public BoardListDto(Board board, String writerNickname) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.hit = board.getHit();
        this.writerId = board.getMemberId();
        this.writerNickname = writerNickname;
        this.registerTime = board.getRegisterTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }
}
