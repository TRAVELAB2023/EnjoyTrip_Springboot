package com.enjoytrip.plan.controller;

import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.plan.dto.PlanDto;
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
    public ResponseEntity<Void> deletePlan(@PathVariable int planId, HttpSession session){
        SessionDto sessionDto= (SessionDto) session.getAttribute("userInfo");
        planService.deletePlan(planId,sessionDto.getMemberId());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<List<Attraction>> getPlan(@PathVariable int planId,HttpSession session){
        SessionDto sessionDto= (SessionDto) session.getAttribute("userInfo");
        List<Attraction> list=planService.findByPlanId(planId,sessionDto.getMemberId());
        return new ResponseEntity<List<Attraction>>(list,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PlanDto>> getPlanList(HttpSession session, @PageableDefault Pageable pageable){
        SessionDto sessionDto= (SessionDto) session.getAttribute("userInfo");
        List<PlanDto> list=planService.findByMemberId(sessionDto.getMemberId(),pageable);
        return new ResponseEntity<List<PlanDto>>(list,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Void> postPlan(HttpSession session,  @RequestBody @Valid PlanRequestDto planRequestDto){
        SessionDto sessionDto= (SessionDto) session.getAttribute("userInfo");
        planService.savePlan(sessionDto.getMemberId(),planRequestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
