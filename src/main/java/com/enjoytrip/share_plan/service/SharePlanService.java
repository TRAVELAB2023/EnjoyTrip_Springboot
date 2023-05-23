package com.enjoytrip.share_plan.service;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.Plan;
import com.enjoytrip.share_plan.dto.SharePlanRequestDto;

import java.util.List;

public interface SharePlanService {
    String registerSharePlan(SharePlanRequestDto planShareRequestDto,int memberId);
    List<Attraction> getSharePlanAttraction(String key);
}
