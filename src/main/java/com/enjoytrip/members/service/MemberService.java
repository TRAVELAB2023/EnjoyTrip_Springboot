package com.enjoytrip.members.service;

import com.enjoytrip.model.Member;

import java.sql.SQLException;

public interface MemberService {

    public Member login(Member member) throws SQLException;
    public void join(Member member) throws SQLException;
    public void dropMember(Member member) throws SQLException;
    public void modifyMemberPassword(Member member) throws SQLException;
    public boolean isDuplicatedId(String id) throws SQLException;
    public boolean isDuplicatedEmail(String email) throws SQLException;
}
