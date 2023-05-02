package com.enjoytrip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "member_like")
public class MemberLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_like_id")
    int memberLikeId;


    @Column(name = "member_id")
    int memberId;

    @Column(name="attraction_info")
    int attractionId;

    @Column(name="register_time")
    LocalDateTime registerTime;

    @Builder
    public MemberLike(int memberLikeId, int memberId, int attractionId, LocalDateTime registerTime) {
        this.memberLikeId = memberLikeId;
        this.memberId = memberId;
        this.attractionId = attractionId;
        this.registerTime = registerTime;
    }
}
