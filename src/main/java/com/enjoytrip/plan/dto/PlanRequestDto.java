package com.enjoytrip.plan.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PlanRequestDto {
    private String planName;
    private List<Integer> contentIdList;
}
