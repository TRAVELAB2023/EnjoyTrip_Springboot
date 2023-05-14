package com.enjoytrip.members.repository;

import com.enjoytrip.members.mapper.MemberIdMapping;
import com.enjoytrip.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMemberByEmailAndPasswordAndDelYn(String email, String password, boolean delYn);
    int countMembersByNickname(String nickname);
    int countMembersByEmail(String Email);
    List<MemberIdMapping> findAllIdByNicknameContains(String nickname);
}
