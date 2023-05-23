package com.enjoytrip.member_like.repository;

import com.enjoytrip.model.MemberLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLikeRepository extends JpaRepository<MemberLike, Integer> {


    void deleteByMemberIdAndAttractionId(int memberId, int attractionId);

    int countByMemberIdAndAttractionId(int memberId, int attractionId);

    List<MemberLike> findByMemberId(int memberId);
}
