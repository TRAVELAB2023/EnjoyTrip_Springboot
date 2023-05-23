package com.enjoytrip.members.service;

import com.enjoytrip.members.dto.LoginDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.model.Member;

import java.sql.SQLException;

public interface MemberService {

    public Member findById(int memberId) throws SQLException;
    public SessionDto login(LoginDto loginDto) throws Exception;
    public int join(RegisterDto registerDto, String role) throws SQLException;
    public void dropMember(int memberId) throws SQLException;
    public void modifyMemberPassword(int memberId, String newPassword) throws SQLException;
    public boolean isDuplicatedNickname(String nickname) throws SQLException;
    public boolean isDuplicatedEmail(String email) throws SQLException;

    public SessionDto findByEmail(String email) throws Exception;

    public boolean isWritePassword(LoginDto loginDto) throws Exception;

    public void saveRefreshToken(String email, String refreshToken) throws Exception;
    public Object getRefreshToken(String email) throws Exception;
    public void deleteRefreshToken(String email) throws Exception;

    SessionDto info(int memberId);
}
