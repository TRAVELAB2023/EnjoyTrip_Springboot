package com.enjoytrip.plan.controller;

import com.enjoytrip.plan.service.PlanService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }
}
