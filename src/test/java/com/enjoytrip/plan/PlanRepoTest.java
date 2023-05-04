package com.enjoytrip.plan;

import com.enjoytrip.model.Plan;
import com.enjoytrip.model.PlanDetail;
import com.enjoytrip.plan.repository.PlanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@PropertySource(value = "classpath:application.properties")
public class PlanRepoTest {
    private Logger logger= LoggerFactory.getLogger(PlanRepoTest.class);


    @Autowired
    private PlanRepository planRepository;


    @Test
    @DisplayName("나의 플랜 목록 출력")
    public void testFindAllPlan(){
        int memberId=12;
        for(int i=0; i<12;i++){
            Plan plan=Plan.builder()
                    .memberId(memberId)
                    .title("테스트 용도 플랜").build();
            plan=planRepository.save(plan);
            plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
            planRepository.save(plan);
        }

        Pageable pageable= PageRequest.of(0,10);
        Page<Plan> list=planRepository.findPageByMemberId(memberId,pageable);

        Assertions.assertEquals(10,list.getContent().size());

    }

    @Test
    @DisplayName("나의 플랜 추가")
    public void testFindPageByMemberId(){
        int memberId=12;
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        plan=planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);

        Assertions.assertEquals(1,planRepository.findAll().size());

    }

}
