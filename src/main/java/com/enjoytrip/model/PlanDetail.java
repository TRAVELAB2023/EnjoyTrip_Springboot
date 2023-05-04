package com.enjoytrip.model;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="plan_detail")
public class PlanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plan_detail_id", nullable = false)
    private Integer planDetailId;

    @Column(name="content_id", nullable = false)
    private int contentId;
    @Column(name="plan_id")
    private int planId;


    @Builder
    public PlanDetail(int contentId, int planId) {
        this.contentId = contentId;
        this.planId = planId;
    }
}
