package com.enjoytrip.notice.repository;

import com.enjoytrip.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findByNoticeId(int noticeId);

}
