package com.enjoytrip;

import com.enjoytrip.member_like.repository.MemberLikeRepository;
import com.enjoytrip.member_like.service.MemberLikeService;
import com.enjoytrip.member_like.service.MemberLikeServiceImpl;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.members.service.MemberServiceImpl;
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

    public SpringConfig(MemberRepository memberRepository, MemberLikeRepository memberLikeRepository, ReportUserRepository reportUserRepository) {
        this.memberRepository = memberRepository;
        this.memberLikeRepository = memberLikeRepository;
        this.reportUserRepository = reportUserRepository;
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

}
