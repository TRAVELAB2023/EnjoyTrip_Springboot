package com.enjoytrip.comment_board.controller;

import com.enjoytrip.comment_board.dto.ReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyUpdateDto;
import com.enjoytrip.comment_board.service.CommentBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/comment")
public class CommentBoardController {
    private final CommentBoardService commentBoardService;

    public CommentBoardController(CommentBoardService commentBoardService) {
        this.commentBoardService = commentBoardService;
    }

    @PostMapping("")
    public ResponseEntity registerReply(@RequestBody ReplyRegisterDto replyRegisterDto) throws Exception {
        int replyId = commentBoardService.registerReply(replyRegisterDto);
        return new ResponseEntity(replyId, HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity registerReplyReply(@RequestBody ReplyReplyRegisterDto replyReplyRegisterDto) throws Exception {
        int replyId = commentBoardService.registerReplyReply(replyReplyRegisterDto);
        return new ResponseEntity(replyId, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity updateReply(@RequestBody ReplyUpdateDto replyUpdateDto) throws Exception {
        commentBoardService.updateReply(replyUpdateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteReply(@PathVariable(name = "comment-id") int commentId) throws Exception {
        commentBoardService.deleteReply(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{comment-id}")
    public ResponseEntity deleteReplyReply(@PathVariable(name = "comment-id") int commentId) throws Exception {
        commentBoardService.deleteReplyReply(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
