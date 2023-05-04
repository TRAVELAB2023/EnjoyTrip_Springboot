package com.enjoytrip.plan.service;

import com.enjoytrip.model.Plan;
import com.enjoytrip.model.PlanDetail;
import com.enjoytrip.plan.dto.PlanDto;
import com.enjoytrip.plan.repository.PlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
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
    public void savePlan(int memberId, String planName, List<Integer> contentIdList) {
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title(planName).build();
        plan=planRepository.save(plan);
        for(Integer contentId : contentIdList){
            plan.add(PlanDetail.builder().contentId(contentId).planId(plan.getPlanId()).build());
        }
        planRepository.save(plan);
    }


}
