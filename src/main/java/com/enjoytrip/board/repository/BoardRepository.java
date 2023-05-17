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

    List<Board> findAllByOrderByRegisterTimeDesc(Pageable pageable);

    List<Board> findByTitleContainsOrderByRegisterTimeDesc(String searchString, Pageable pageable);

    List<Board> findByContentContainsOrderByRegisterTimeDesc(String searchString, Pageable pageable);

    List<Board> findByTitleContainsOrContentContainsOrderByRegisterTimeDesc(String searchString, String searchString1, Pageable pageable);

    List<Board> findByMemberIdInOrderByRegisterTimeDesc(List<Integer> memberIdList, Pageable pageable);
}
