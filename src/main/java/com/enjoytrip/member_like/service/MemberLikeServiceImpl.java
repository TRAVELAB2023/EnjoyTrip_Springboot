package com.enjoytrip.member_like.service;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.member_like.repository.MemberLikeRepository;
import com.enjoytrip.model.MemberLike;

import javax.transaction.Transactional;
import java.sql.SQLException;

public class MemberLikeServiceImpl implements MemberLikeService {

    private final MemberLikeRepository memberLikeRepository;

    public MemberLikeServiceImpl(MemberLikeRepository memberLikeRepository) {
        this.memberLikeRepository = memberLikeRepository;
    }

    @Override
    @Transactional
    public int registerLike(MemberLikeDto memberLikeDto,int memberId) throws SQLException{
        if(isCanFind(memberId,memberLikeDto.getAttractionId())){
            memberLikeRepository.deleteByMemberIdAndAttractionId(memberId, memberLikeDto.getAttractionId());
            return 0;
        }else{
            MemberLike memberLike = MemberLike.builder()
                    .memberId(memberId)
                    .attractionId(memberLikeDto.getAttractionId())
                    .build();
            memberLikeRepository.save(memberLike);
            return 1;
        }
    }

    @Override
    public boolean isCanFind(int memberId, int attractionId)throws SQLException {
        if (memberLikeRepository.countByMemberIdAndAttractionId(memberId, attractionId) == 1) {
            return true;
        }
        return false;
    }
}
