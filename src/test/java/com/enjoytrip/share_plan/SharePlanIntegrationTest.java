package com.enjoytrip.share_plan;

import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.*;
import com.enjoytrip.plan.repository.PlanRepository;
import com.enjoytrip.share_plan.dto.SharePlanRequestDto;
import com.enjoytrip.share_plan.repository.SharePlanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SharePlanIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private SharePlanRepository sharePlanRepository;
    private ObjectMapper objectMapper;
    private MockHttpSession mockHttpSession;

    private Member member;

    @BeforeEach
    public void init(){
        objectMapper=new ObjectMapper();
        member=memberRepository.findMemberByEmailAndPasswordAndDelYn("admin@admin.com","admin",false);
        SessionDto sessionDto=new SessionDto(member);
        mockHttpSession=new MockHttpSession();
        mockHttpSession.setAttribute("userInfo", sessionDto);
    }

    @Test
    @DisplayName("계획 공유하기 테스트")
    public void sharePlanTest() throws Exception {
        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);
        SharePlanRequestDto sharePlanRequestDto= SharePlanRequestDto.builder().planId(plan.getPlanId()).haltDate(LocalDate.of(2100,10,10)).build();

        mockMvc.perform(post("/share/plan")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sharePlanRequestDto)))
                .andExpect(status().isOk());


    }
    @Test
    @DisplayName("나의 것이 아닌 계획 공유하기 테스트")
    public void sharePlanAuthExceptionTest() throws Exception {
        int memberId=433;
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);

        SharePlanRequestDto sharePlanRequestDto= SharePlanRequestDto.builder().planId(plan.getPlanId()).haltDate(LocalDate.of(2100,10,10)).build();

        mockMvc.perform(post("/share/plan")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sharePlanRequestDto)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("권한이 없습니다."));
    }
    @Test
    @DisplayName("없는 계획 공유하기 테스트")
    public void sharePlanExceptionTest() throws Exception {
        SharePlanRequestDto sharePlanRequestDto= SharePlanRequestDto.builder().planId(90000000).haltDate(LocalDate.of(2100,10,10)).build();

        mockMvc.perform(post("/share/plan")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sharePlanRequestDto)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("계획을 찾을 수 없습니다."));
    }

    @Test
    @DisplayName("공유된 계획 조회 테스트")
    public void getSharePlanTest() throws Exception {
        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);
        SharePlanRequestDto sharePlanRequestDto= SharePlanRequestDto.builder().planId(plan.getPlanId()).haltDate(LocalDate.of(2100,10,10)).build();
        SharePlan sharePlan=SharePlan.builder().urlKey(UUID.randomUUID().toString()).haltTime(sharePlanRequestDto.getHaltDate()).plan(plan).build();
        sharePlanRepository.save(sharePlan);


        String key=sharePlan.getUrlKey();
        MvcResult result=mockMvc.perform(get("/share/plan/"+key)
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Attraction> list= Arrays.asList(objectMapper.readValue(result.getResponse().getContentAsString(),Attraction[].class));
        Assertions.assertEquals(list.size(),3);

    }
    @Test
    @DisplayName("없는 계획 조회 테스트")
    public void getSharePlanExceptionTest() throws Exception {
        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);
        SharePlanRequestDto sharePlanRequestDto= SharePlanRequestDto.builder().planId(plan.getPlanId()).haltDate(LocalDate.of(2100,10,10)).build();
        SharePlan sharePlan=SharePlan.builder().urlKey(UUID.randomUUID().toString()).haltTime(sharePlanRequestDto.getHaltDate()).plan(plan).build();
        sharePlanRepository.save(sharePlan);


        String key = "gkroegrepgrekpkekopperp";
        mockMvc.perform(get("/share/plan/"+key)
                            .session(mockHttpSession)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string("데이터를 찾을 수 없습니다."));


    }


}
