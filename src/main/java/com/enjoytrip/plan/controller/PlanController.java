package com.enjoytrip.plan.controller;

import com.enjoytrip.common.LoginMember;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.plan.dto.PlanDto;
import com.enjoytrip.plan.dto.PlanListResponseDto;
import com.enjoytrip.plan.dto.PlanRequestDto;
import com.enjoytrip.plan.service.PlanService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable int planId, @LoginMember int memberId){
        planService.deletePlan(planId,memberId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<List<Attraction>> getPlan(@PathVariable int planId,@LoginMember int memberId){
        List<Attraction> list=planService.findByPlanId(planId,memberId);
        return new ResponseEntity<List<Attraction>>(list,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<PlanListResponseDto> getPlanList(@LoginMember int memberId, @PageableDefault Pageable pageable){
        PlanListResponseDto responseDto=planService.findByMemberId(memberId,pageable);
        return new ResponseEntity<PlanListResponseDto>(responseDto,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> postPlan(@LoginMember int memberId,  @RequestBody @Valid PlanRequestDto planRequestDto){
        planService.savePlan(memberId,planRequestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
