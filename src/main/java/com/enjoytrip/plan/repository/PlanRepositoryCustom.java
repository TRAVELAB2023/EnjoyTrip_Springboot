package com.enjoytrip.plan.repository;

import com.enjoytrip.model.Attraction;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Attraction> findAttractionByPlanId(int planId);
}
