package com.enjoytrip.util;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class SearchCondition{
    String searchString;

    /*
    0: none
    1: title
    2: content
    3: title & content
    4: member nickname
     */
    int searchType;

    public SearchCondition(String searchString, int searchType) {
        this.searchString = searchString;
        this.searchType = searchType;
    }
}
