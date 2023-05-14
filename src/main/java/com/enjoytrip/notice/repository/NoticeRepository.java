package com.enjoytrip.notice.repository;

import com.enjoytrip.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findByNoticeId(int noticeId);

    Page<Notice> findAllByOrderByRegisterTimeDesc(Pageable pageable);
    Page<Notice> findByTitleContainsOrderByRegisterTimeDesc (String title, Pageable pageable);

    Page<Notice> findByContentContainsOrderByRegisterTimeDesc(String Content, Pageable pageable);
    Page<Notice> findByTitleContainsOrContentContainsOrderByRegisterTimeDesc(String title, String content, Pageable pageable);
    Page<Notice> findByMemberIdInOrderByRegisterTimeDesc(List<Integer> memberIds, Pageable pageable);

    void deleteByNoticeId(int noticeId);


}
