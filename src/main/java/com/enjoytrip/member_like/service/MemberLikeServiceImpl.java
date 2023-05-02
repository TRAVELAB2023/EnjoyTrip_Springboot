package com.enjoytrip.member_like.service;

import com.enjoytrip.member_like.repository.MemberLikeRepository;
import com.enjoytrip.model.MemberLike;

import java.sql.SQLException;

public class MemberLikeServiceImpl implements MemberLikeService {

    private final MemberLikeRepository memberLikeRepository;

    public MemberLikeServiceImpl(MemberLikeRepository memberLikeRepository) {
        this.memberLikeRepository = memberLikeRepository;
    }

    @Override
    public void registerLike(MemberLike memberLike) throws SQLException{
        memberLikeRepository.save(memberLike);
    }

    @Override
    public void deleteLike(int memberId, int attractionId)throws SQLException {
        memberLikeRepository.deleteByMemberIdAndAttractionId(memberId,attractionId);

    }

    @Override
    public boolean isCanFind(int memberId, int attractionId)throws SQLException {
        if (memberLikeRepository.countByMemberIdAndAttractionId(memberId, attractionId) == 1) {
            return true;
        }
        return false;
    }
}
