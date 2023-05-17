package com.enjoytrip.board.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class BoardRegisterDto {
    String title;
    int member_id;
    String content;

    public BoardRegisterDto(String title, int member_id, String content) {
        this.title = title;
        this.member_id = member_id;
        this.content = content;
    }
}
