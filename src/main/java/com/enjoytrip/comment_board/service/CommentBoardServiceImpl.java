package com.enjoytrip.comment_board.service;

import com.enjoytrip.comment_board.dto.ReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyReplyRegisterDto;
import com.enjoytrip.comment_board.dto.ReplyUpdateDto;
import com.enjoytrip.comment_board.repository.CommentBoardRepository;
import com.enjoytrip.model.CommentBoard;
import org.springframework.stereotype.Service;

@Service
public class CommentBoardServiceImpl implements CommentBoardService{

    private final CommentBoardRepository commentBoardRepository;

    public CommentBoardServiceImpl(CommentBoardRepository commentBoardRepository) {
        this.commentBoardRepository = commentBoardRepository;
    }

    @Override
    public int registerReply(ReplyRegisterDto registerDto) {
        CommentBoard commentBoard = CommentBoard.builder()
                .boardId(registerDto.getBoardId())
                .content(registerDto.getContent())
                .memberId(registerDto.getMemberId())
                .build();
        commentBoard = commentBoardRepository.save(commentBoard);
        commentBoard.setRgroup();


        return commentBoard.getCommentId();
    }

    @Override
    public int registerReplyReply(ReplyReplyRegisterDto replyReplyRegisterDto) {
        CommentBoard commentBoard = CommentBoard.builder()
                .boardId(replyReplyRegisterDto.getBoardId())
                .content(replyReplyRegisterDto.getContent())
                .memberId(replyReplyRegisterDto.getMemberId())
                .rgroup(replyReplyRegisterDto.getRgroup())
                .replyDepth(true)
                .build();
        return commentBoardRepository.save(commentBoard).getCommentId();
    }

    @Override
    public void deleteReply(int replyId) {
        CommentBoard commentBoard=commentBoardRepository.findByCommentId(replyId);
        int rgroup = commentBoard.getRgroup();
        commentBoardRepository.deleteAllByRgroup(rgroup);
    }

    @Override
    public void deleteReplyReply(int replyId) {
        commentBoardRepository.deleteByCommentId(replyId);
    }

    @Override
    public void updateReply(ReplyUpdateDto replyUpdateDto) {
        CommentBoard commentBoard=commentBoardRepository.findByCommentId(replyUpdateDto.getReplyId());
        commentBoard.updateContent(replyUpdateDto.getContent());
    }
}
