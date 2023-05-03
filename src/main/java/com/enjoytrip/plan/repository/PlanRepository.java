package com.enjoytrip.plan.repository;

import com.enjoytrip.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Integer> {
}
