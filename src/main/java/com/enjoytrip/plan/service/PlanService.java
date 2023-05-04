package com.enjoytrip.plan.service;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.plan.dto.PlanDto;
import com.enjoytrip.plan.dto.PlanRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {


    public List<PlanDto> findByMemberId(int memberId,Pageable pageable);

    public void savePlan(int memberId, PlanRequestDto planRequestDto);

    public void deletePlan(int planId,int memberId);

    public List<Attraction> findByPlanId(int planId, int memberId);
}
