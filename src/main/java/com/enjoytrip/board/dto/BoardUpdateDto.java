package com.enjoytrip.board.dto;

import lombok.Getter;

@Getter
public class BoardUpdateDto {
    int boardId;
    String title;
    String content;

    public BoardUpdateDto(int boardId, String title, String content) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
    }
}
