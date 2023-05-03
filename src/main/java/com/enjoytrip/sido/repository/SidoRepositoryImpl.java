package com.enjoytrip.sido.repository;

import com.enjoytrip.model.Gugun;
import com.enjoytrip.model.QGugun;
import com.enjoytrip.model.QSido;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SidoRepositoryImpl implements SidoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    public SidoRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Gugun> getGugunList(int sidoCode) {
        return jpaQueryFactory.select(QGugun.gugun)
                .from(QSido.sido)
                .where(QSido.sido.sidoCode.eq(sidoCode))
                .leftJoin(QSido.sido.gugunList,QGugun.gugun)
                .fetch();
    }
}
