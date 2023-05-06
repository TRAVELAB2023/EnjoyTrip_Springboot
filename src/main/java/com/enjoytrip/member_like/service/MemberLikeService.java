package com.enjoytrip.member_like.service;

import com.enjoytrip.member_like.dto.MemberLikeDto;
import com.enjoytrip.model.MemberLike;

import java.sql.SQLException;

public interface MemberLikeService {
    void registerLike(MemberLikeDto memberLikeDto)throws SQLException;
    void deleteLike(MemberLikeDto memberLikeDto)throws SQLException;

    boolean isCanFind(int memberId, int attractionId)throws SQLException;
}
