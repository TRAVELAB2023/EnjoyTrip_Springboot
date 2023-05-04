package com.enjoytrip.plan;

import com.enjoytrip.exception.PlanException;
import com.enjoytrip.exception.PlanExceptionMessage;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.Member;
import com.enjoytrip.model.Plan;
import com.enjoytrip.model.PlanDetail;
import com.enjoytrip.plan.repository.PlanRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@PropertySource(value = "classpath:application.properties")
public class PlanRepoTest {
    private Logger logger= LoggerFactory.getLogger(PlanRepoTest.class);


    @Autowired
    private PlanRepository planRepository;


    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    @BeforeEach
    public void init(){
        member=Member.builder().email("test@naver.com")
                .nickname("nickname")
                .password("1234")
                .role("USER")
                .build();
        memberRepository.save(member);
    }
    @Test
    @DisplayName("나의 플랜 목록 출력")
    public void testFindAllPlan(){
        int memberId=member.getMemberId();
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
        int memberId=member.getMemberId();
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

    @Test
    @DisplayName("계획 플랜 삭제(데이터가 있을 때)")
    public void testDeletePlan(){

        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        plan=planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        planRepository.save(plan);

        plan=planRepository.findByPlanId(plan.getPlanId())
                .orElseThrow(() -> new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
        if(plan.getMemberId()!=memberId){
            throw new PlanException(PlanExceptionMessage.NO_AUTH);
        }
        planRepository.delete(plan);

        Assertions.assertEquals(Optional.empty(),planRepository.findByPlanId(plan.getPlanId()));
    }


    @Test
    @DisplayName("계획 플랜 삭제(데이터가 없을 때)")
    public void testDeletePlanEmpty(){
        Assertions.assertThrows(PlanException.class,()->{
            Plan plan=planRepository.findByPlanId(231341414)
                    .orElseThrow(() -> new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
            if(plan.getMemberId()!=1355151){
                throw new PlanException(PlanExceptionMessage.NO_AUTH);
            }
            planRepository.delete(plan);
    });
    }

    @Test
    @DisplayName("플랜에 포함된 관광지 조회")
    public void testFindByPlanId(){

        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        plan=planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        planRepository.save(plan);

        List<Attraction> list=planRepository.findAttractionByPlanId(plan.getPlanId());
        logger.debug("데이터 : {}",list);
        Assertions.assertEquals(2,list.size());


    }
    @Test
    @DisplayName("다른 사람의 플랜에 포함된 관광지 조회(예외 처리)")
    public void testFindByPlanIdException(){

        int memberId=member.getMemberId();
        int anotherMemberId=Integer.MAX_VALUE;
        final Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        planRepository.save(plan);
        Assertions.assertThrows(PlanException.class,()-> {
            Plan plan2=planRepository.findByPlanId(plan.getPlanId())
                    .orElseThrow(() -> new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
            if(plan2.getMemberId()!=anotherMemberId){
                throw new PlanException(PlanExceptionMessage.NO_AUTH);
            }
            planRepository.findAttractionByPlanId(plan.getPlanId());
        });






    }
}
