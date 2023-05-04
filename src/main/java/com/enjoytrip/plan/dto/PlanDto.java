package com.enjoytrip.plan.dto;

import com.enjoytrip.model.Plan;
import com.enjoytrip.model.PlanDetail;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PlanDto {

    private int planId;

    private String title;

    private LocalDateTime register_time;

    public PlanDto(Plan plan) {
        this.planId = plan.getPlanId();;
        this.title = plan.getTitle();
        this.register_time = plan.getRegisterTime();
    }
}
