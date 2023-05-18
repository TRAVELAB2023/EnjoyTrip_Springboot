package com.enjoytrip.comment_board.repository;

import com.enjoytrip.model.CommentBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentBoardRepository extends JpaRepository<CommentBoard, Integer> {
    List<CommentBoard> findByBoardId(int boardId);
    CommentBoard findByCommentId(int commentId);
    void deleteAllByRgroup(int rgroup);

    void deleteByCommentId(int commentId);
}
