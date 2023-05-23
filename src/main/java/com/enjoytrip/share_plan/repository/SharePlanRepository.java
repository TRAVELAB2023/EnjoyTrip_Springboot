package com.enjoytrip.share_plan.repository;

import com.enjoytrip.model.SharePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SharePlanRepository extends JpaRepository<SharePlan,Integer> {
    SharePlan findByUrlKeyAndHaltTimeAfter(String urlKey, LocalDate localDate);
}
