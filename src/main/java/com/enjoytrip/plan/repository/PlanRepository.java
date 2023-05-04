package com.enjoytrip.plan.repository;

import com.enjoytrip.model.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Integer>,PlanRepositoryCustom {
    public Page<Plan> findPageByMemberId(int memberId, Pageable pageable);

    public Optional<Plan> findByPlanId(int planId);

}
