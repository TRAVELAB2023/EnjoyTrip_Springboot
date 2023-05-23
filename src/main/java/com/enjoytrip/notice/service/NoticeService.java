package com.enjoytrip.notice.service;

import com.enjoytrip.notice.dto.*;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    int register(NoticeRegisterDto noticeDto) throws Exception;

    NoticePageDto noticeList(SearchCondition searchCondition, Pageable pageable) throws Exception;

    NoticeDetailDto detail(int noticeId) throws Exception;

    void delete(int noticeId) throws Exception;

    void update(NoticeUpdateDto noticeUpdateDto);
}
