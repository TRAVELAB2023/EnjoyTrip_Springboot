package com.enjoytrip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class SharePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_plan_id")
    private int sharePlanId;

    @Column(name = "url_key")
    private String urlKey;
    @Column(name = "halt_time")
    private LocalDate haltTime;

    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Builder
    public SharePlan(String urlKey, LocalDate haltTime,Plan plan){
        this.urlKey=urlKey;
        this.haltTime=haltTime;
        this.plan=plan;
    }
}
