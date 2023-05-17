package com.enjoytrip.board.repository;

import com.enjoytrip.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByBoardId(int boardId);
}
