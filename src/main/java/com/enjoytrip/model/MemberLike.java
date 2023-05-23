package com.enjoytrip.model;

import com.enjoytrip.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "member_like")
public class MemberLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_like_id")
    int memberLikeId;


    @Column(name = "member_id")
    int memberId;

    @Column(name="attraction_info")
    int attractionId;

    @Builder
    public MemberLike(int memberLikeId, int memberId, int attractionId) {
        this.memberLikeId = memberLikeId;
        this.memberId = memberId;
        this.attractionId = attractionId;
    }
}
