package com.enjoytrip.notice.controller;

import com.enjoytrip.notice.dto.*;
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
    public ResponseEntity list(@RequestParam(required = false,defaultValue = "") String searchString,
                               @RequestParam(required = false,defaultValue = "0") int searchType,
                               @RequestParam(required = false,defaultValue = "0") int start,
                               @RequestParam(required = false,defaultValue = "10") int size)throws Exception{
        NoticeSearchDto noticeSearchDto = new NoticeSearchDto(searchString, searchType, start, size);
        SearchCondition searchCondition = new SearchCondition(noticeSearchDto.getSearchString(), noticeSearchDto.getSearchType());
        NoticePageDto noticePageDto=noticeService.noticeList(searchCondition, PageRequest.of(noticeSearchDto.getStart(), noticeSearchDto.getSize()));


        return new ResponseEntity<>(noticePageDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity register(@RequestBody NoticeRegisterDto noticeRegisterDto) throws Exception {
        System.out.println(noticeRegisterDto);
        int noticeId= noticeService.register(noticeRegisterDto);
        return new ResponseEntity(noticeId,HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity modify(@RequestBody NoticeUpdateDto noticeUpdateDto) throws Exception {
        noticeService.update(noticeUpdateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{notice-id}")
    public ResponseEntity<NoticeDetailDto> delete(@PathVariable(name = "notice-id") int noticeId) throws Exception {
        noticeService.delete(noticeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
