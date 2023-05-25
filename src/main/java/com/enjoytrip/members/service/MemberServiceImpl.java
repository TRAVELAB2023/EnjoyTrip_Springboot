package com.enjoytrip.members.service;

import com.enjoytrip.exception.custom_exception.MemberException;
import com.enjoytrip.exception.message.MemberExceptionMessage;
import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.ModifyPasswordDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Member;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    public int join(RegisterDto registerDto, String role) throws SQLException {
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
                .role(role)
                .build();

        return memberRepository.save(member).getMemberId();
    }

    @Override
    @Transactional
    public void dropMember(int memberId) throws SQLException {
        Member member = findById(memberId);
        member.dropMember();
    }

    @Override
    @Transactional
    public void modifyMemberPassword(int memberId, ModifyPasswordDto modifyPasswordDto) throws SQLException {
        Member member = findById(memberId);
        if(member.getPassword().equals(modifyPasswordDto.getCurPassword())){
            member.modifyPassword(modifyPasswordDto.getNewPassword());
        }else{
            throw new MemberException(MemberExceptionMessage.NOT_EQUALS_PASSWORD);
        }

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
    public SessionDto findByEmail(String email) throws Exception {
        Member member = memberRepository.findByEmailAndDelYn(email, false);
        if (member == null) {
            throw new MemberException(MemberExceptionMessage.WRONG_ID_OR_PASSWORD);
        }
        return new SessionDto(member);
    }

    @Override
    public boolean isRightPassword(String password, int memberId) throws Exception {
        Member member = memberRepository.findByMemberId(memberId);
        if (member == null || !member.getPassword().equals(password)) {
            throw new MemberException(MemberExceptionMessage.WRONG_PASSWORD);
        }

        return true;
    }

    @Override
    @Transactional
    public void saveRefreshToken(String email, String refreshToken) throws Exception {
        Member member = memberRepository.findByEmailAndDelYn(email, false);
        member.updateToken(refreshToken);
    }

    @Override
    public String getRefreshToken(String email) throws Exception {
        return memberRepository.findByEmailAndDelYn(email, false).getToken();
    }

    @Override
    @Transactional
    public void deleteRefreshToken(String email) throws Exception {
        Member member = memberRepository.findByEmailAndDelYn(email, false);
        member.updateToken(null);
    }

    @Override
    public SessionDto info(int memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return new SessionDto(member);
    }


}
