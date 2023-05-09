package com.enjoytrip.notice.service;

import com.enjoytrip.model.Notice;
import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public void register(NoticeRegisterDto registerDto) throws Exception {
        Notice notice = Notice.builder()
                .memberId(registerDto.getMemberId())
                .title(registerDto.getTitle())
                .content(registerDto.getContent())
                .build();
        noticeRepository.save(notice);
    }

    @Override
    public List<NoticeListDto> noticeList() throws Exception {
        List<Notice> NoticeList = noticeRepository.findAll();
        List<NoticeListDto> noticeListDtoList = new ArrayList<>();
        for (Notice notice : NoticeList) {
            noticeListDtoList.add(new NoticeListDto(notice));
        }

        return noticeListDtoList;
    }

    @Override
    public NoticeDetailDto detail(int noticeId) throws Exception {
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        return new NoticeDetailDto(notice);
    }
}
