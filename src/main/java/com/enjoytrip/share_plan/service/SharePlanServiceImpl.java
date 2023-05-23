package com.enjoytrip.share_plan.service;


import com.enjoytrip.exception.custom_exception.PlanException;
import com.enjoytrip.exception.custom_exception.SharePlanException;
import com.enjoytrip.exception.message.PlanExceptionMessage;
import com.enjoytrip.exception.message.SharePlanExceptionMessage;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.Plan;
import com.enjoytrip.model.SharePlan;
import com.enjoytrip.plan.repository.PlanRepository;
import com.enjoytrip.share_plan.dto.SharePlanRequestDto;
import com.enjoytrip.share_plan.repository.SharePlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SharePlanServiceImpl implements SharePlanService {


    private final SharePlanRepository sharePlanRepository;

    private final PlanRepository planRepository;
    public SharePlanServiceImpl(SharePlanRepository sharePlanRepository, PlanRepository planRepository) {
        this.sharePlanRepository = sharePlanRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public void registerSharePlan(SharePlanRequestDto sharePlanRequestDto,int memberId) {
        Plan plan=planRepository.findByPlanId(sharePlanRequestDto.getPlanId()).orElseThrow(()-> new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
        if(plan.getMemberId()!=memberId){
            throw new PlanException(PlanExceptionMessage.NO_AUTH);
        }
        SharePlan sharePlan=SharePlan.builder().urlKey(UUID.randomUUID().toString()).haltTime(sharePlanRequestDto.getHaltDate()).plan(plan).build();
        sharePlanRepository.save(sharePlan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attraction> getSharePlanAttraction(String key) {
        SharePlan sharePlan=sharePlanRepository.findByUrlKeyAndHaltTimeAfter(key, LocalDate.now());
        if(sharePlan==null){
            throw new SharePlanException(SharePlanExceptionMessage.DATA_NOT_FOUND);
        }
        Plan plan=sharePlan.getPlan();
        if(plan==null){
            throw new PlanException(PlanExceptionMessage.DATA_NOT_FOUND);
        }
        return  planRepository.findAttractionByPlanId(plan.getPlanId());
    }
}
