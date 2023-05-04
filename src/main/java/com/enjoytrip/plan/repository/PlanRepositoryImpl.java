package com.enjoytrip.plan.repository;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.QAttraction;
import com.enjoytrip.model.QPlanDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class PlanRepositoryImpl implements PlanRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    public PlanRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public List<Attraction> findAttractionByPlanId(int planId) {
        return jpaQueryFactory.select(QAttraction.attraction)
                .from(QPlanDetail.planDetail)
                .join(QAttraction.attraction).on(QAttraction.attraction.contentId.eq(QPlanDetail.planDetail.contentId))
                .where(QPlanDetail.planDetail.planId.eq(planId))
                .fetch();
    }
}
