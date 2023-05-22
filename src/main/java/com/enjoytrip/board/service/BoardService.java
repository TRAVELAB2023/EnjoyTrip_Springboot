package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.*;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    int register(BoardRegisterDto boardRegisterDto) throws IOException;
    BoardDto detail(int boardId);
    BoardPageDto list(SearchCondition searchCondition, Pageable pageable);
    void update(BoardUpdateDto boardUpdateDto);
    void delete(int boardId);
}
