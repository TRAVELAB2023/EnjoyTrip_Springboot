package com.enjoytrip.board.controller;

import com.enjoytrip.board.dto.*;
import com.enjoytrip.board.service.BoardService;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/{board-id}")
    public ResponseEntity<BoardDto> detail(@PathVariable(name = "board-id") int boardId) throws Exception {
        BoardDto boardDto = boardService.detail(boardId);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity list(@RequestParam(required = false,defaultValue = "") String searchString,
                                                   @RequestParam(required = false,defaultValue = "0") int searchType,
                                                   @RequestParam(required = false,defaultValue = "0") int start,
                                                   @RequestParam(required = false,defaultValue = "10") int size){

        BoardSearchDto boardSearchDto = new BoardSearchDto(searchString, searchType, start, size);
        SearchCondition searchCondition = new SearchCondition(boardSearchDto.getSearchString(), boardSearchDto.getSearchType());
        BoardPageDto boardPageDto = boardService.list(searchCondition, PageRequest.of(boardSearchDto.getStart(), boardSearchDto.getSize()));
        return new ResponseEntity<>(boardPageDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity register(@RequestBody BoardRegisterDto boardRegisterDto) throws Exception {
        int boardId= boardService.register(boardRegisterDto);
        return new ResponseEntity(boardId,HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity delete(@PathVariable(name = "board-id") int boardId) throws Exception {
        boardService.delete(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity update(@RequestBody BoardUpdateDto boardUpdateDto) throws Exception {
        boardService.update(boardUpdateDto);
        return new ResponseEntity(HttpStatus.OK);
    }


}
