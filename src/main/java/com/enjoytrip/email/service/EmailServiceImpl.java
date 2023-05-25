package com.enjoytrip.email.service;

import com.enjoytrip.email.dto.EmailRequestDto;
import com.enjoytrip.email.repository.TempPasswordRepository;
import com.enjoytrip.exception.custom_exception.EmailException;
import com.enjoytrip.exception.message.EmailExceptionMessage;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Member;
import com.enjoytrip.model.TempPassword;
import com.enjoytrip.util.EmailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class EmailServiceImpl implements EmailService{
    private final EmailUtil emailUtil;
    private final MemberRepository memberRepository;
    private final TempPasswordRepository tempPasswordRepository;
    public EmailServiceImpl(EmailUtil emailUtil, MemberRepository memberRepository, TempPasswordRepository tempPasswordRepository) {
        this.emailUtil = emailUtil;
        this.memberRepository = memberRepository;
        this.tempPasswordRepository = tempPasswordRepository;
    }

    @Override
    @Transactional
    public void sendEmail(EmailRequestDto emailRequestDto) {
        Member member=memberRepository.findByEmailAndDelYn(emailRequestDto.getEmail(),false);
        if(member!=null){
            String tempKey=UUID.randomUUID().toString();
            String tempPw= UUID.randomUUID().toString().replace("-","").substring(0,10);

            TempPassword tempPassword=TempPassword.builder()
                                                .tempKey(tempKey)
                                                .tempPw(tempPw)
                                                .member(member)
                                                .build();
            tempPasswordRepository.save(tempPassword);
            emailUtil.sendPW(tempPassword);
        }

    }

    @Override
    @Transactional
    public void checkEmail(String key) {
        TempPassword tempPassword=tempPasswordRepository.findByTempKey(key).orElseThrow(()->new EmailException(EmailExceptionMessage.DATA_NOT_FOUND));
        tempPassword.getMember().modifyPassword(tempPassword.getTempPw());

    }
}
