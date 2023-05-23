package com.enjoytrip.email.service;

import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.util.EmailUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class EmailServiceImpl implements EmailService{
    private final EmailUtil emailUtil;
    private final MemberRepository memberRepository;

    public EmailServiceImpl(EmailUtil emailUtil, MemberRepository memberRepository) {
        this.emailUtil = emailUtil;
        this.memberRepository = memberRepository;
    }

    @Override
    public void sendEmail(String email, HttpSession session) {
        if(memberRepository.countMembersByEmail(email)!=0){
            emailUtil.sendPW(email,session);
        }

    }
}
