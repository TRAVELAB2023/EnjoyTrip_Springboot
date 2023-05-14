package com.enjoytrip.notice.service;

import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.notice.util.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    int register(NoticeRegisterDto noticeDto) throws Exception;

    List<NoticeListDto> noticeList(SearchCondition searchCondition, Pageable pageable) throws Exception;

    NoticeDetailDto detail(int noticeId) throws Exception;

    void delete(int noticeId) throws Exception;

}
