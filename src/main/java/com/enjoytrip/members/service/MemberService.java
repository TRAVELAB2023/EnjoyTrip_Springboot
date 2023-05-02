package com.enjoytrip.members.service;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.MemberDto;
import com.enjoytrip.model.Member;

import java.sql.SQLException;

public interface MemberService {

    public Member findById(int memberId) throws SQLException;
    public MemberDto login(LoginDto loginDto) throws SQLException;
    public void join(Member member) throws SQLException;
    public void dropMember(int memberId) throws SQLException;
    public void modifyMemberPassword(int memberId, String newPassword) throws SQLException;
    public boolean isDuplicatedNickname(String nickname) throws SQLException;
    public boolean isDuplicatedEmail(String email) throws SQLException;
}
