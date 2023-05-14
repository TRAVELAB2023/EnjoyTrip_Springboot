package com.enjoytrip.plan.dto;

import com.enjoytrip.model.Plan;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlanDto {

    private int planId;

    private String title;

    @JsonDeserialize(using= LocalDateTimeDeserializer.class)
    private LocalDateTime register_time;



    public PlanDto(Plan plan) {
        this.planId = plan.getPlanId();;
        this.title = plan.getTitle();
        this.register_time = plan.getRegisterTime();
    }
}
