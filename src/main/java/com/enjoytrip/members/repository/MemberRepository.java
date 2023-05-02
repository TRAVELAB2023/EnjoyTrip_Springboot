package com.enjoytrip.members.repository;

import com.enjoytrip.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;


public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMemberByEmailAndPasswordAndDelYn(String email, String password, boolean delYn);
    int countMembersByNickname(String nickname);
    int countMembersByEmail(String Email);

}
