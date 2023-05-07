package com.enjoytrip.notice.service;

import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;

import java.util.List;

public interface NoticeService {
    void register(NoticeRegisterDto noticeDto) throws Exception;
    List<NoticeListDto> NoticeList() throws Exception;
    NoticeDetailDto detail(int noticeId) throws Exception;
}
