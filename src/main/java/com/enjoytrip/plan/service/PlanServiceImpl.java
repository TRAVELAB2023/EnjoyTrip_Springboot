package com.enjoytrip.plan.service;

import com.enjoytrip.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService{
    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }
}
