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
    public Member login(Member member) throws SQLException {
        return memberRepository.findMemberByEmailAndPasswordAndDelYn(member.getEmail(), member.getPassword(),false);
    }

    @Override
    public void join(Member member) throws SQLException {

    }

    @Override
    public void dropMember(Member member) throws SQLException {

    }

    @Override
    public void modifyMemberPassword(Member member) throws SQLException {

    }

    @Override
    public boolean isDuplicatedId(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean isDuplicatedEmail(String email) throws SQLException {
        return false;
    }
}
