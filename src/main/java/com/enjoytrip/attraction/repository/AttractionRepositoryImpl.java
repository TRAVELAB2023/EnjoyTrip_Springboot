package com.enjoytrip.attraction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class AttractionRepositoryImpl implements AttractionRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;


    public AttractionRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


}
