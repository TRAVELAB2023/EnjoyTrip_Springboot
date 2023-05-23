package com.enjoytrip.member_like.service;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.model.MemberLike;

import java.sql.SQLException;

public interface MemberLikeService {
    int registerLike(MemberLikeDto memberLikeDto,int memberId)throws SQLException;

    boolean isCanFind(int memberId, int attractionId)throws SQLException;
}
