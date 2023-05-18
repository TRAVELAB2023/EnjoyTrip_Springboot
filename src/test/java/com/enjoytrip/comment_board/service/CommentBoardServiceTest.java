package com.enjoytrip.comment_board.service;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.board.service.BoardService;
import com.enjoytrip.comment_board.dto.ReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyUpdateDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.io.IOException;
import java.sql.SQLException;


@SpringBootTest
@Transactional
class CommentBoardServiceTest {
    @Autowired
    CommentBoardService commentBoardService;
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    int boardId = 0;
    int memberId = 0;

    @BeforeEach
    void before() throws SQLException, IOException {
        memberId = memberService.join(new RegisterDto("test@test", "test", true, "test"),"user");
        boardId= boardService.register(new BoardRegisterDto("test", memberId, "test"));
    }

    @Test
    void registerReply() {
        commentBoardService.registerReply(new ReplyRegisterDto("좋아요",memberId,boardId));
        BoardDto boardDto = boardService.detail(boardId);
        Assertions.assertEquals(boardDto.getCommentBoardList().get(0).getContent(),"좋아요");
    }

    @Test
    void registerReplyReply() {
        int rgroup= commentBoardService.registerReply(new ReplyRegisterDto("좋아요",memberId,boardId));

         commentBoardService.registerReplyReply(new ReplyReplyRegisterDto("좋아요좋아요", memberId, boardId, rgroup));
        BoardDto boardDto = boardService.detail(boardId);
        System.out.println(boardDto);
    }

    @Test
    void deleteReply(){
        int id = 0;
        for (int i = 1; i <= 5; i++) {
            id = commentBoardService.registerReply(new ReplyRegisterDto("좋아요"+i,memberId,boardId));
        }
        commentBoardService.deleteReply(id);
        BoardDto boardDto = boardService.detail(boardId);
        Assertions.assertEquals(boardDto.getCommentBoardList().size(),4);
    }

    @Test
    void updateReply(){
         int id = commentBoardService.registerReply(new ReplyRegisterDto("좋아요",memberId,boardId));

        commentBoardService.updateReply(new ReplyUpdateDto(id, "싫어요"));
        BoardDto boardDto = boardService.detail(boardId);
        Assertions.assertEquals(boardDto.getCommentBoardList().get(0).getContent(),"싫어요");
    }

    @Test
    void deleteReplyAndReplayReplay(){
        int id = commentBoardService.registerReply(new ReplyRegisterDto("좋아요",memberId,boardId));
        commentBoardService.registerReply(new ReplyRegisterDto("좋아요",memberId,boardId));

        for (int i = 0; i < 5; i++) {
            commentBoardService.registerReplyReply(new ReplyReplyRegisterDto("좋아요좋아요", memberId, boardId, id));
        }
        commentBoardService.deleteReply(id);
        BoardDto boardDto = boardService.detail(boardId);
        Assertions.assertEquals(boardDto.getCommentBoardList().size(),1);
    }
}