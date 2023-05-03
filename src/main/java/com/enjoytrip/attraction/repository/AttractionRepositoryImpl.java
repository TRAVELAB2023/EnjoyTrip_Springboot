package com.enjoytrip.attraction.repository;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.QAttraction;
import com.enjoytrip.model.QGugun;
import com.enjoytrip.model.SearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class AttractionRepositoryImpl implements AttractionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    public AttractionRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Attraction> findBySearchCondtion(SearchCondition condition) {

        return jpaQueryFactory.selectFrom(QAttraction.attraction)
                .where(Alleq(condition))
                .offset(0)
                .limit(200)
                .fetch();


    }

    private BooleanBuilder Alleq(SearchCondition condition) {
        BooleanBuilder builder= new BooleanBuilder();
        if(condition.getGugunCode()!=0){
            builder.and(QAttraction.attraction.gugunCode.eq(condition.getGugunCode()));
        }
        if(condition.getSidoCode()!=0){
            builder.and(QAttraction.attraction.sidoCode.eq(condition.getSidoCode()));
        }
        if(condition.getContentTypeCode()!=0){
            builder.and(QAttraction.attraction.contentTypeId.eq(condition.getContentTypeCode()));
        }
        if(StringUtils.hasText(condition.getWord())){
            builder.and(QAttraction.attraction.title.containsIgnoreCase(condition.getWord()));
        }
        return builder;
    }
}
