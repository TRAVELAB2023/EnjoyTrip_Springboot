package com.enjoytrip.board.repository;

import com.enjoytrip.board.dto.BoardListDto;
import com.enjoytrip.model.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByBoardId(int boardId);
    Board findByBoardIdAndDelYn(int boardId, boolean delYn);

    List<Board> findAllByAndDelYnOrderByRegisterTimeDesc(Pageable pageable, boolean delYn);

    List<Board> findByTitleContainsAndDelYnOrderByRegisterTimeDesc(String searchString, Pageable pageable, boolean delYn);

    List<Board> findByContentContainsAndDelYnOrderByRegisterTimeDesc(String searchString, Pageable pageable, boolean delYn);

    List<Board> findByTitleContainsOrContentContainsAndDelYnOrderByRegisterTimeDesc(String searchString, String searchString1, Pageable pageable, boolean delYn);

    List<Board> findByMemberIdInAndDelYnOrderByRegisterTimeDesc(List<Integer> memberIdList, Pageable pageable, boolean delYn);
}
