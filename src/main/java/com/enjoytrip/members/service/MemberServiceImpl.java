package com.enjoytrip.members.service;

import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Member login(Member member) throws SQLException {
        return memberRepository.findMemberByEmailAndPasswordAndDelYn(member.getEmail(), member.getPassword(), false);
    }

    @Override
    public void join(Member member) throws SQLException {
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
        if( memberRepository.countMembersByNickname(nickname)==1){
            return true;
        }

        return false;
    }


    @Override
    public boolean isDuplicatedEmail(String email) throws SQLException {
        if( memberRepository.countMembersByEmail(email)==1){
            return true;
        }

        return false;
    }
}
