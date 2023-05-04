package com.enjoytrip.plan.service;

import com.enjoytrip.exception.PlanException;
import com.enjoytrip.exception.PlanExceptionMessage;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.Plan;
import com.enjoytrip.model.PlanDetail;
import com.enjoytrip.plan.dto.PlanDto;
import com.enjoytrip.plan.dto.PlanRequestDto;
import com.enjoytrip.plan.repository.PlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanDto> findByMemberId(int memberId,Pageable pageable) {
        Page<Plan> planList=planRepository.findPageByMemberId(memberId,pageable);
        List<PlanDto> resultList=new ArrayList<>();
        for(Plan plan : planList.getContent()){
            resultList.add(new PlanDto(plan));
        }
        return resultList;
    }

    @Override
    @Transactional
    public void savePlan(int memberId, PlanRequestDto planRequestDto) {
        String planName=planRequestDto.getPlanName();
        List<Integer> contentIdList=planRequestDto.getContentIdList();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title(planName).build();
        plan=planRepository.save(plan);
        for(Integer contentId : contentIdList){
            plan.add(PlanDetail.builder().contentId(contentId).planId(plan.getPlanId()).build());
        }
        planRepository.save(plan);
    }

    @Override
    @Transactional
    public void deletePlan(int planId,int memberId) {
        Plan plan=planRepository.findByPlanId(planId)
                                .orElseThrow(() -> new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
        if(plan.getMemberId()!=memberId){
            throw new PlanException(PlanExceptionMessage.NO_AUTH);
        }
        planRepository.delete(plan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attraction> findByPlanId(int planId,int memberId) {
        Plan plan=planRepository.findByPlanId(planId)
                                .orElseThrow(() -> new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
        if(plan.getMemberId()!=memberId){
            throw new PlanException(PlanExceptionMessage.NO_AUTH);
        }
        return planRepository.findAttractionByPlanId(planId);
    }


}
