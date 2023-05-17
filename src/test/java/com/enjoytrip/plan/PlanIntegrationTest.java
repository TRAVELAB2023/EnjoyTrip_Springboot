package com.enjoytrip.plan;


import com.enjoytrip.exception.custom_exception.PlanException;
import com.enjoytrip.exception.message.PlanExceptionMessage;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.Member;
import com.enjoytrip.model.Plan;
import com.enjoytrip.model.PlanDetail;
import com.enjoytrip.plan.dto.PlanDto;
import com.enjoytrip.plan.dto.PlanRequestDto;
import com.enjoytrip.plan.repository.PlanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanIntegrationTest {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PlanRepository planRepository;

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
    @DisplayName("게획 삭제")
    public void deletePlanTest() throws Exception {
        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        plan=planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);

        mockMvc.perform(delete("/plan/"+plan.getPlanId())
                        .session(mockHttpSession))
                        .andExpect(status().isOk());



    }
    @Test
    @DisplayName("없는 게획 삭제(예외 처리)")
    public void deletePlanExceptionTest() throws Exception {
        org.assertj.core.api.Assertions.assertThatThrownBy(()->{
            mockMvc.perform(delete("/plan/1111111")
                    .session(mockHttpSession));
        }).hasCause(new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));

    }
    @Test
    @DisplayName("계획에 포함된 관광지 조회")
    public void getPlanTest() throws Exception {
        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        plan=planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);
        MvcResult result=mockMvc.perform(get("/plan/"+plan.getPlanId()))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        List<Attraction> list=Arrays.asList(objectMapper.readValue(result.getResponse().getContentAsString(),Attraction[].class));
        Assertions.assertEquals(list.size(),3);
    }

    @Test
    @DisplayName("존재하지 않는 계획에 포함된 관광지 조회(예외 처리)")
    public void getPlanExceptionTest(){
        org.assertj.core.api.Assertions.assertThatThrownBy(()->{
            mockMvc.perform(get("/plan/1111111")
                    .session(mockHttpSession));
        }).hasCause(new PlanException(PlanExceptionMessage.DATA_NOT_FOUND));
    }
    @Test
    @DisplayName("나의 게획 조회")
    public void getPlanListTest() throws Exception {
        int memberId=member.getMemberId();
        Plan plan=Plan.builder()
                .memberId(memberId)
                .title("테스트 용도 플랜").build();
        plan=planRepository.save(plan);
        plan.add(PlanDetail.builder().contentId(125266).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125405).planId(plan.getPlanId()).build());
        plan.add(PlanDetail.builder().contentId(125406).planId(plan.getPlanId()).build());
        planRepository.save(plan);
        MvcResult result=mockMvc.perform(get("/plan")
                                .session(mockHttpSession))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andReturn();
        List<PlanDto> list=Arrays.asList(objectMapper.readValue(result.getResponse().getContentAsString(), PlanDto[].class));
        Assertions.assertEquals(list.size(),10);
    }
    @Test
    @DisplayName("게획 등록 테스트")
    public void postPlanTest() throws Exception {
        String contentName="content";
        List<Integer> contentIdList=new ArrayList<>();
        contentIdList.add(125266);
        contentIdList.add(125405);
        contentIdList.add(125406);
        PlanRequestDto requestDto=new PlanRequestDto(contentName,contentIdList);

        String json=objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/plan")
                .session(mockHttpSession)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("게획 등록 시 없는 관광지 테스트")
    public void postPlanExceptionTest() throws Exception {
        String contentName="저의새로운여행으로다음여행이기대된다.빨리내일이되었으면좋겠다.";
        List<Integer> contentIdList=new ArrayList<>();
        contentIdList.add(125266);
        contentIdList.add(125405);
        contentIdList.add(125406);
        PlanRequestDto requestDto=new PlanRequestDto(contentName,contentIdList);

        String json=objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/plan")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect((result ->{
                            Assertions.assertTrue(result.getResolvedException().getClass().isAssignableFrom(MethodArgumentNotValidException.class));
                        }));
    }
}
