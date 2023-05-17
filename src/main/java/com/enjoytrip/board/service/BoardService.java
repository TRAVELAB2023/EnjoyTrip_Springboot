package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardListDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    int register(BoardRegisterDto boardRegisterDto, List<MultipartFile> images) throws IOException;
    BoardDto detail(int boardId);
    List<BoardListDto> list(SearchCondition searchCondition, Pageable pageable);
    void update(BoardRegisterDto boardRegisterDto, List<MultipartFile> images);
    void delete(int boardId);
}
