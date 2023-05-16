package com.enjoytrip.members.service;

import com.enjoytrip.exception.MemberException;
import com.enjoytrip.exception.MemberExceptionMessage;
import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Member;

import java.sql.SQLException;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member findById(int memberId) throws SQLException {
        return memberRepository.findById(memberId).get();
    }

    @Override
    public SessionDto login(LoginDto loginDto) throws Exception {
        Member member = memberRepository.findMemberByEmailAndPasswordAndDelYn(loginDto.getEmail(), loginDto.getPassword(), false);
        if (member == null) {
            throw new MemberException(MemberExceptionMessage.WRONG_ID_OR_PASSWORD);
        }
        SessionDto sessionDto = new SessionDto(member);

        return sessionDto;
    }

    @Override
    public void join(RegisterDto registerDto) throws SQLException {
        if(isDuplicatedEmail(registerDto.getEmail())&&isDuplicatedNickname(registerDto.getNickname())){
            throw new MemberException(MemberExceptionMessage.DUPLICATED_EMAIL_AND_NICKNAME);
        } else if (isDuplicatedEmail(registerDto.getEmail())) {
            throw new MemberException(MemberExceptionMessage.DUPLICATED_EMAIL);
        } else if (isDuplicatedNickname(registerDto.getNickname())) {
            throw new MemberException(MemberExceptionMessage.DUPLICATED_NICKNAME);
        }
        Member member = Member.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .marketingAgreement(registerDto.isMarketing())
                .nickname(registerDto.getNickname())
                .role(registerDto.getRole())
                .build();

        memberRepository.save(member);
    }

    @Override
    public void dropMember(int memberId) throws SQLException {
        Member member = findById(memberId);
        member.dropMember();
    }

    @Override
    public void modifyMemberPassword(int memberId, String newPassword) throws SQLException {
        Member member = findById(memberId);
        member.modifyPassword(newPassword);
    }

    @Override
    public boolean isDuplicatedNickname(String nickname) throws SQLException {
        if (memberRepository.countMembersByNickname(nickname) == 1) {
            return true;
        }

        return false;
    }


    @Override
    public boolean isDuplicatedEmail(String email) throws SQLException {
        if (memberRepository.countMembersByEmail(email) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isWritePassword(LoginDto loginDto) throws Exception {
        if (login(loginDto) != null) {
            return true;
        }

        return false;
    }
}
