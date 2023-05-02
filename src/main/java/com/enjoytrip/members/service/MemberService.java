package com.enjoytrip.members.service;

import com.enjoytrip.model.Member;

import java.sql.SQLException;

public interface MemberService {

    public Member findById(int memberId) throws SQLException;
    public Member login(Member member) throws SQLException;
    public void join(Member member) throws SQLException;
    public void dropMember(int memberId) throws SQLException;
    public void modifyMemberPassword(int memberId, String newPassword) throws SQLException;
    public boolean isDuplicatedId(String id) throws SQLException;
    public boolean isDuplicatedEmail(String email) throws SQLException;
}
