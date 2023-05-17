package com.enjoytrip.board.repository;

import com.enjoytrip.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    Image findByBoardId(int boardId);
}
