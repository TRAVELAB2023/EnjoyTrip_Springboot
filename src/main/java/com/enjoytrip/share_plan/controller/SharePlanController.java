package com.enjoytrip.share_plan.controller;


import com.enjoytrip.common.LoginMember;
import com.enjoytrip.members.dto.SessionDto;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.share_plan.dto.SharePlanRequestDto;
import com.enjoytrip.share_plan.service.SharePlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/share/plan")
public class SharePlanController {

    private final SharePlanService planShareService;

    public SharePlanController(SharePlanService planShareService) {
        this.planShareService = planShareService;
    }

    @PostMapping("")
    public ResponseEntity<String> registerSharePlan(@LoginMember int memberId, @RequestBody @Valid SharePlanRequestDto planShareRequestDto){
        String key=planShareService.registerSharePlan(planShareRequestDto,memberId);
        return new ResponseEntity<String>(key,HttpStatus.OK);
    }
    @GetMapping("/{key}")
    public ResponseEntity<List<Attraction>> getSharePlan(@PathVariable String key){
        List<Attraction> list=planShareService.getSharePlanAttraction(key);
        return new ResponseEntity<List<Attraction>>(list,HttpStatus.OK);
    }
}
