package com.enjoytrip.plan.dto;

import com.enjoytrip.model.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlanListResponseDto {
    private int totalPage;
    List<PlanDto> resultList;

    public PlanListResponseDto(Page<Plan> planList) {
        resultList=new ArrayList<>();
        this.totalPage=planList.getTotalPages();
        for(Plan plan : planList.getContent()){
            resultList.add(new PlanDto(plan));
        }
    }
}
