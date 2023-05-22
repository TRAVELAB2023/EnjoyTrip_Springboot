package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.board.dto.BoardUpdateDto;
import com.enjoytrip.exception.custom_exception.BoardException;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.*;
import java.sql.SQLException;


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
        memberId = memberService.join(new RegisterDto("test@test", "test", true, "test"),"user");

    }

    @Test
    void registerAndDetail() throws IOException {
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto("test", memberId, "test");
        int boardId = boardService.register(boardRegisterDto);
        BoardDto boardDto = boardService.detail(boardId);
        Assertions.assertTrue(
                boardRegisterDto.getContent().equals(boardDto.getContent())
                        && boardRegisterDto.getTitle().equals(boardDto.getTitle())
                        && boardRegisterDto.getMemberId() == boardDto.getWriterId()

        );
    }

    @Test
    void update() throws IOException {
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto("test", memberId, "test");
        int boardId = boardService.register(boardRegisterDto);
        BoardDto boardDto = boardService.detail(boardId);

        BoardUpdateDto boardUpdateDto = new BoardUpdateDto(boardDto.getBoardId(), boardDto.getTitle(), "변경");
        boardService.update(boardUpdateDto);
        BoardDto boardDto2 = boardService.detail(boardId);

        Assertions.assertEquals(boardDto2.getContent(),boardUpdateDto.getContent());
    }

    @Test
    void delete() throws IOException {
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto("test", memberId, "test");
        int boardId = boardService.register(boardRegisterDto);
        boardService.delete(boardId);
        Assertions.assertThrows(BoardException.class, () -> boardService.detail(boardId));
    }
}