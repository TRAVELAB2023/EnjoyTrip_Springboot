package com.enjoytrip;

import com.enjoytrip.board.repository.BoardRepository;
import com.enjoytrip.board.repository.CommentBoardRepository;
import com.enjoytrip.board.repository.ImageRepository;
import com.enjoytrip.board.service.BoardService;
import com.enjoytrip.board.service.BoardServiceImpl;
import com.enjoytrip.member_like.repository.MemberLikeRepository;
import com.enjoytrip.member_like.service.MemberLikeService;
import com.enjoytrip.member_like.service.MemberLikeServiceImpl;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.members.service.MemberServiceImpl;
import com.enjoytrip.notice.repository.NoticeRepository;
import com.enjoytrip.notice.service.NoticeService;
import com.enjoytrip.notice.service.NoticeServiceImpl;
import com.enjoytrip.report_user.repository.ReportUserRepository;
import com.enjoytrip.report_user.service.ReportUserService;
import com.enjoytrip.report_user.service.ReportUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final MemberLikeRepository memberLikeRepository;
    private final ReportUserRepository reportUserRepository;
    private final NoticeRepository noticeRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final CommentBoardRepository commentBoardRepository;
    public SpringConfig(MemberRepository memberRepository, MemberLikeRepository memberLikeRepository, ReportUserRepository reportUserRepository, NoticeRepository noticeRepository, BoardRepository boardRepository, ImageRepository imageRepository, CommentBoardRepository commentBoardRepository) {
        this.memberRepository = memberRepository;
        this.memberLikeRepository = memberLikeRepository;
        this.reportUserRepository = reportUserRepository;
        this.noticeRepository = noticeRepository;
        this.boardRepository = boardRepository;
        this.imageRepository = imageRepository;
        this.commentBoardRepository = commentBoardRepository;
    }

    @Bean
    MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }

    @Bean
    MemberLikeService memberLikeService() {
        return new MemberLikeServiceImpl(memberLikeRepository);
    }

    @Bean
    ReportUserService reportUserService() {
        return new ReportUserServiceImpl(reportUserRepository);
    }

    @Bean
    NoticeService noticeService(){
        return new NoticeServiceImpl(noticeRepository,memberRepository);
    }

    @Bean
    BoardService boardService() {
        return new BoardServiceImpl(boardRepository, imageRepository, commentBoardRepository, memberRepository);
    }

}
