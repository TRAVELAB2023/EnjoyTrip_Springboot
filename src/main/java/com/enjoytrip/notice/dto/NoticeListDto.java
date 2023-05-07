package com.enjoytrip.notice.dto;

import com.enjoytrip.model.Notice;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
public class NoticeListDto {
    int noticeId;
    String title;
    LocalDateTime registerTime;
    int memberId;

    public NoticeListDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.title = notice.getTitle();
        this.registerTime = notice.getRegisterTime();
        this.memberId = notice.getMemberId();
    }
}
