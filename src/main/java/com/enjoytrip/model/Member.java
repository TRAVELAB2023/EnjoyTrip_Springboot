package com.enjoytrip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void dropMember() {
        this.delYn = true;
    }

    public void modifyPassword(String newPassword) {
        this.password = newPassword;
    }

}
