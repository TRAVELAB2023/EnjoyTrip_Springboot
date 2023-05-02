package com.enjoytrip.member_like.service;

import com.enjoytrip.model.MemberLike;

import java.sql.SQLException;

public interface MemberLikeService {
    void registerLike(MemberLike memberLike)throws SQLException;
    void deleteLike(int memberId, int attractionId)throws SQLException;

    boolean isCanFind(int memberId, int attractionId)throws SQLException;
}
