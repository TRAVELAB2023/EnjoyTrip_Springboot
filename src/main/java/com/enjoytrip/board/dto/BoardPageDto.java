package com.enjoytrip.board.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardPageDto {
    int page;
    List<BoardListDto> boardListDtoList;

    public BoardPageDto(int page, List<BoardListDto> boardListDtoList) {
        this.page = page;
        this.boardListDtoList = boardListDtoList;
    }
}
