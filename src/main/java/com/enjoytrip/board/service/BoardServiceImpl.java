package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.*;
import com.enjoytrip.board.repository.BoardRepository;
import com.enjoytrip.board.repository.CommentBoardRepository;
import com.enjoytrip.board.repository.ImageRepository;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Board;
import com.enjoytrip.model.CommentBoard;
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
    private final CommentBoardRepository commentBoardRepository;
    private final MemberRepository memberRepository;
    private final String path = "src/main/resources/file/image/";

    public BoardServiceImpl(BoardRepository boardRepository, ImageRepository imageRepository, CommentBoardRepository commentBoardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.imageRepository = imageRepository;
        this.commentBoardRepository = commentBoardRepository;
        this.memberRepository = memberRepository;
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
        List<CommentBoardDto> commentBoardDtoList = getCommentBoardDtoList(boardId);
        List<ImageDto> imageDtoList = getImageDtoList(boardId);
        Board board = boardRepository.findByBoardId(boardId);
        String writerNickname = memberRepository.findByMemberId(board.getMemberId()).getNickname();

        return new BoardDto(board,commentBoardDtoList,imageDtoList,writerNickname);
    }

    private List<ImageDto> getImageDtoList(int boardId) {
        List<Image> Images = imageRepository.findByBoardId(boardId);
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (Image image : Images) {
            imageDtoList.add(new ImageDto(image));
        }
        return imageDtoList;
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
        return null;
    }

    @Override
    public void update(BoardRegisterDto boardRegisterDto, List<MultipartFile> images) {

    }

    @Override
    public void delete(int boardId) {

    }
}
