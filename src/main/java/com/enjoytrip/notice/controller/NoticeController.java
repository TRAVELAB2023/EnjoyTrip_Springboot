package com.enjoytrip.notice.controller;

import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.notice.dto.NoticeSearchDto;
import com.enjoytrip.notice.service.NoticeService;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @GetMapping("/{notice-id}")
    public ResponseEntity<NoticeDetailDto> detail(@PathVariable(name = "notice-id") int noticeId) throws Exception {
        NoticeDetailDto noticeDetailDto = noticeService.detail(noticeId);
        return new ResponseEntity<>(noticeDetailDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoticeListDto>> list(@RequestBody NoticeSearchDto noticeSearchDto) throws Exception{
        SearchCondition searchCondition = new SearchCondition(noticeSearchDto.getSearchString(), noticeSearchDto.getSearchType());

        List<NoticeListDto> list = noticeService.noticeList(searchCondition, PageRequest.of(noticeSearchDto.getStart(), noticeSearchDto.getSize()));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity register(@RequestBody NoticeRegisterDto noticeRegisterDto) throws Exception {
        int noticeId= noticeService.register(noticeRegisterDto);
        return new ResponseEntity(noticeId,HttpStatus.OK);
    }

    @DeleteMapping("/{notice-id}")
    public ResponseEntity<NoticeDetailDto> delete(@PathVariable(name = "notice-id") int noticeId) throws Exception {
        noticeService.delete(noticeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
