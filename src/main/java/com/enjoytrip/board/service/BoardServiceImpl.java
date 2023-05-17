package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.BoardDto;
import com.enjoytrip.board.dto.BoardListDto;
import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.board.repository.BoardRepository;
import com.enjoytrip.board.repository.ImageRepository;
import com.enjoytrip.model.Board;
import com.enjoytrip.model.Image;
import com.enjoytrip.util.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final String path = "src/main/resources/file/image/";

    public BoardServiceImpl(BoardRepository boardRepository, ImageRepository imageRepository) {
        this.boardRepository = boardRepository;
        this.imageRepository = imageRepository;
    }


    @Override
    public int register(BoardRegisterDto boardRegisterDto, List<MultipartFile> images) throws IOException {
        Board board = Board.builder()
                .title(boardRegisterDto.getTitle())
                .content(boardRegisterDto.getContent())
                .memberId(boardRegisterDto.getMember_id())
                .build();
        int boardId = boardRepository.save(board).getBoardId();
        List<Image> imageModels = new ArrayList<>();
        for (MultipartFile image : images) {
            String fileName = image.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            UUID uuid = UUID.randomUUID();
            String newFileName = uuid.toString() + extension;
            image.transferTo(new File(path + newFileName));
            imageModels.add(Image.builder()
                    .boardId(boardId)
                    .extension(newFileName)
                    .build());
        }
        imageRepository.saveAll(imageModels);

        return boardId;
    }

    @Override
    public BoardDto detail(int boardId) {
        return null;
    }

    @Override
    public List<BoardListDto> list(SearchCondition searchCondition, Pageable pageable) {
        return null;
    }

    @Override
    public void update(BoardRegisterDto boardRegisterDto, List<MultipartFile> images) {

    }

    @Override
    public void delete(int boardId) {

    }
}
