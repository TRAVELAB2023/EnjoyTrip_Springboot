package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardListDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    int register(BoardRegisterDto boardRegisterDto);
    BoardDto detail(int boardId);
    List<BoardListDto> list(SearchCondition searchCondition, Pageable pageable);
    void update(BoardRegisterDto boardRegisterDto);
    void delete(int boardId);
}
