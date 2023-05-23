package com.enjoytrip.notice.dto;

import lombok.Getter;

@Getter
public class NoticeRegisterDto {
    String title;
    int memberId;
    String content;

    public NoticeRegisterDto(String title, int memberId, String content) {
        this.title = title;
        this.memberId = memberId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoticeRegisterDto{" +
                "title='" + title + '\'' +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                '}';
    }
}
