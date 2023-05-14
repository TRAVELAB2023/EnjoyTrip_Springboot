package com.enjoytrip.members.service;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.model.Member;

import java.sql.SQLException;

public interface MemberService {

    public Member findById(int memberId) throws SQLException;
    public SessionDto login(LoginDto loginDto) throws SQLException;
    public int join(RegisterDto registerDto) throws SQLException;
    public void dropMember(int memberId) throws SQLException;
    public void modifyMemberPassword(int memberId, String newPassword) throws SQLException;
    public boolean isDuplicatedNickname(String nickname) throws SQLException;
    public boolean isDuplicatedEmail(String email) throws SQLException;
}
