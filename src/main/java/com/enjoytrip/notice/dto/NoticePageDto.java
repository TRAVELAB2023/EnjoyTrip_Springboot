package com.enjoytrip.notice.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class NoticePageDto {
    int page;
    List<NoticeListDto> list;

    public NoticePageDto(int page, List<NoticeListDto> list) {
        this.page = page;
        this.list = list;
    }
}
