package com.enjoytrip.notice.dto;

import com.enjoytrip.model.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeDetailDto {
    int noticeId;
    String title;
    LocalDateTime registerTime;
    int memberId;
    String content;

    public NoticeDetailDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.title = notice.getTitle();
        this.registerTime = notice.getRegisterTime();
        this.memberId = notice.getMemberId();
        this.content = notice.getContent();
    }
}
