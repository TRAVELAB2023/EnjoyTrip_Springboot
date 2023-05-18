package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.*;
import com.enjoytrip.board.repository.BoardRepository;
import com.enjoytrip.comment_board.repository.CommentBoardRepository;
import com.enjoytrip.exception.custom_exception.BoardException;
import com.enjoytrip.exception.message.BoardExceptionMessage;
import com.enjoytrip.members.mapper.MemberIdMapping;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Board;
import com.enjoytrip.model.CommentBoard;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CommentBoardRepository commentBoardRepository;
    private final MemberRepository memberRepository;

    public BoardServiceImpl(BoardRepository boardRepository, CommentBoardRepository commentBoardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.commentBoardRepository = commentBoardRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public int register(BoardRegisterDto boardRegisterDto) throws IOException {
        Board board = Board.builder()
                .title(boardRegisterDto.getTitle())
                .content(boardRegisterDto.getContent())
                .memberId(boardRegisterDto.getMember_id())
                .build();
        return boardRepository.save(board).getBoardId();
    }

    @Override
    public BoardDto detail(int boardId) {
        Board board = boardRepository.findByBoardIdAndDelYn(boardId,false);
        if (board == null) {
            throw new BoardException(BoardExceptionMessage.WRONG_PAGE);
        }
        List<CommentBoardDto> commentBoardDtoList = getCommentBoardDtoList(boardId);
        String writerNickname = memberRepository.findByMemberId(board.getMemberId()).getNickname();

        return new BoardDto(board, commentBoardDtoList, writerNickname);
    }

    private List<CommentBoardDto> getCommentBoardDtoList(int boardId) {
        List<CommentBoard> commentBoards = commentBoardRepository.findByBoardId(boardId);
        List<CommentBoardDto> commentBoardDtoList = new ArrayList<>();
        for (CommentBoard commentBoard : commentBoards) {
            commentBoardDtoList.add(new CommentBoardDto(commentBoard, memberRepository.findByMemberId(commentBoard.getMemberId()).getNickname()));
        }

        return commentBoardDtoList;
    }

    @Override
    public List<BoardListDto> list(SearchCondition searchCondition, Pageable pageable) {
        List<Board> boards = getBoardList(searchCondition, pageable);
        List<BoardListDto> boardListDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardListDtoList.add(new BoardListDto(board, memberRepository.findByMemberId(board.getMemberId()).getNickname()));
        }

        return boardListDtoList;
    }

    private List<Board> getBoardList(SearchCondition searchCondition, Pageable pageable) {
        if (searchCondition.getSearchType() == 1) {
            return boardRepository.findByTitleContainsAndDelYnOrderByRegisterTimeDesc(searchCondition.getSearchString(), pageable,false);
        } else if (searchCondition.getSearchType() == 2) {
            return boardRepository.findByContentContainsAndDelYnOrderByRegisterTimeDesc(searchCondition.getSearchString(), pageable,false);
        } else if (searchCondition.getSearchType() == 3) {
            return boardRepository.findByTitleContainsOrContentContainsAndDelYnOrderByRegisterTimeDesc(searchCondition.getSearchString(), searchCondition.getSearchString(), pageable,false);
        } else if (searchCondition.getSearchType() == 4) {
            List<Integer> memberIdList = getMemberIdListByNicknameLike(searchCondition.getSearchString());
            return boardRepository.findByMemberIdInAndDelYnOrderByRegisterTimeDesc(memberIdList, pageable,false);
        }
        return boardRepository.findAllByAndDelYnOrderByRegisterTimeDesc(pageable,false);
    }

    private List<Integer> getMemberIdListByNicknameLike(String nickname) {
        List<MemberIdMapping> memberIdMappings = memberRepository.findAllIdByNicknameContains(nickname);
        List<Integer> result = new ArrayList<>();
        for (MemberIdMapping memberIdMapping : memberIdMappings) {
            result.add(memberIdMapping.getMemberId());
        }
        return result;
    }

    @Override
    public void update(BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findByBoardId(boardUpdateDto.getBoardId());
        board.updateContent(boardUpdateDto.getContent());
        board.updateTitle(boardUpdateDto.getTitle());
    }

    @Override
    public void delete(int boardId) {
        Board board = boardRepository.findByBoardId(boardId);
        board.deleteBoard();
    }
}
