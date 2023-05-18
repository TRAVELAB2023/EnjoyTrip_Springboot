package com.enjoytrip.board.dto;

import lombok.Getter;

@Getter
public class BoardSearchDto {
    String searchString;

    /*
    0: none
    1: title
    2: content
    3: title & content
    4: member nickname
     */
    int searchType;
    int start;
    int size;

    public BoardSearchDto(String searchString, int searchType, int start, int size) {
        this.searchString = searchString;
        this.searchType = searchType;
        this.start = start;
        this.size = size;
    }
}
