package com.enjoytrip.notice.controller;

import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.notice.service.NoticeService;
import com.enjoytrip.notice.util.SearchCondition;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<NoticeListDto>> list(@RequestParam Map<String, String> map) throws Exception{
        int searchType = Integer.parseInt(map.get("type"));
        String searchString = map.get("searchString");
        SearchCondition searchCondition = new SearchCondition(searchString, searchType);
        int startIndex= Integer.parseInt(map.get("start"));
        int size = Integer.parseInt(map.get("size"));

        List<NoticeListDto> list = noticeService.noticeList(searchCondition, PageRequest.of(startIndex, size));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity register(@RequestParam NoticeRegisterDto noticeRegisterDto) throws Exception {
        noticeService.register(noticeRegisterDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{notice-id}")
    public ResponseEntity<NoticeDetailDto> delete(@PathVariable(name = "notice-id") int noticeId) throws Exception {
        noticeService.delete(noticeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
