package com.enjoytrip.members.service;

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
    public SessionDto login(LoginDto loginDto) throws SQLException {
        SessionDto sessionDto = new SessionDto(memberRepository.findMemberByEmailAndPasswordAndDelYn(loginDto.getEmail(), loginDto.getPassword(), false));
        return sessionDto;
    }

    @Override
    public void join(RegisterDto registerDto) throws SQLException {
        Member member = Member.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .marketingAgreement(registerDto.isMarketingAgreement())
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
}
