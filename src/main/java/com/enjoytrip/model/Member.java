package com.enjoytrip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;
    private String email;
    private String nickname;
    private String password;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "marketing_agreement")
    private boolean marketingAgreement;
    private String role;

    @Column(name = "delYn")
    private boolean delYn;

    @Builder
    public Member(int memberId, String email, String nickname, String password, LocalDateTime registerTime, boolean marketingAgreement, String role, boolean delYn) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.registerTime = registerTime;
        this.marketingAgreement = marketingAgreement;
        this.role = role;
        this.delYn = delYn;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", marketingAgreement=" + marketingAgreement +
                ", role='" + role + '\'' +
                ", delYn=" + delYn +
                '}';
    }

    public void dropMember(){
        this.delYn=true;
    }
}
