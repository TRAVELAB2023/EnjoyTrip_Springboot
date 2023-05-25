package com.enjoytrip.model;

import com.enjoytrip.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "temp_pw")
@NoArgsConstructor
public class TempPassword extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="temp_pw_id")
    private int tempPwId;

    @Column(name="temp_key")
    private String tempKey;

    @Column(name="temp_pw")
    private String tempPw;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public TempPassword(String tempKey,String tempPw,Member member){
        this.tempKey=tempKey;
        this.tempPw=tempPw;
        this.member=member;
    }


}
