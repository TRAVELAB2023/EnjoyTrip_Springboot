package com.enjoytrip.plan.service;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.plan.dto.PlanDto;
import com.enjoytrip.plan.dto.PlanRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {


    // 내가 세웠던 계획을 조회한다.
    public List<PlanDto> findByMemberId(int memberId,Pageable pageable);

    // 계획을 저장한다.
    public void savePlan(int memberId, PlanRequestDto planRequestDto);

    // 계획을 삭제한다.
    public void deletePlan(int planId,int memberId);

    // 특정 플랜의 계획되 관광지를 조회한다.
    public List<Attraction> findByPlanId(int planId,int memberId);
}
