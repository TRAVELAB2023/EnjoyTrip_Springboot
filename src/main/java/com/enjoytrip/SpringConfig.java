package com.enjoytrip;

import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.members.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean MemberService memberService(){
        return new MemberServiceImpl(memberRepository);
    }


}
