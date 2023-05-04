package com.enjoytrip.plan.service;

import com.enjoytrip.plan.dto.PlanDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {


    public List<PlanDto> findByMemberId(int memberId,Pageable pageable);

    public void savePlan(int memberId, String planName,List<Integer> contentIdList);
}
