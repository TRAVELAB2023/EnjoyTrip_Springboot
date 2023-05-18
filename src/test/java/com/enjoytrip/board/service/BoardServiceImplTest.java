package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Transactional
class BoardServiceImplTest {
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    int memberId = 0;

    @BeforeEach
    void before() throws SQLException, IOException {
        memberId = memberService.join(new RegisterDto("test@test", "test", true, "test"));

    }

    @Test
    void registerAndDetail() throws IOException {
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto("test", memberId, "test");
        int boardId = boardService.register(boardRegisterDto);
        BoardDto boardDto = boardService.detail(boardId);
        Assertions.assertTrue(
                boardRegisterDto.getContent().equals(boardDto.getContent())
                        && boardRegisterDto.getTitle().equals(boardDto.getTitle())
                        && boardRegisterDto.getMember_id() == boardDto.getWriterId()

        );
    }

}