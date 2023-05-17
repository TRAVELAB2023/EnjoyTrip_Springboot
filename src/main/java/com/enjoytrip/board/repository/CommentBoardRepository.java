package com.enjoytrip.board.repository;

import com.enjoytrip.model.CommentBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentBoardRepository extends JpaRepository<CommentBoard, Integer> {
    List<CommentBoard> findByBoardId(int boardId);
}
