package com.enjoytrip.model;


import com.enjoytrip.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "plan")
@NoArgsConstructor
public class Plan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Integer planId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "plan_id")
    private List<PlanDetail> planDetailList;


    @Builder
    public Plan(String title, int memberId) {
        this.title = title;
        this.memberId = memberId;
    }


    public void add(PlanDetail planDetail) {
        if(planDetailList==null){
            planDetailList=new ArrayList<>();
        }
        planDetailList.add(planDetail);
    }

}
