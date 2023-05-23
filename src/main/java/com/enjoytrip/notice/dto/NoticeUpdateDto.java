package com.enjoytrip.notice.dto;

import lombok.Getter;

@Getter
public class NoticeUpdateDto {
    int id;
    String title;
    String content;

    public NoticeUpdateDto(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
