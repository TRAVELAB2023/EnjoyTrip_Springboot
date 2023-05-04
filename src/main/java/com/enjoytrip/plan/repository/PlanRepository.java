package com.enjoytrip.plan.repository;

import com.enjoytrip.model.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Integer> {
    public Page<Plan> findPageByMemberId(int memberId, Pageable pageable);
}
