package com.enjoytrip.notice.dto;

import com.enjoytrip.model.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListDto {
    int boardId;
    String title;
    LocalDateTime registerTime;
    int memberId;

    public NoticeListDto(Notice notice) {
        this.boardId = notice.getNoticeId();
        this.title = notice.getTitle();
        this.registerTime = notice.getRegisterTime();
        this.memberId = notice.getMemberId();
    }
}
