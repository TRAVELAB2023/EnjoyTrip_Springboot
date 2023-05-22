package com.enjoytrip.plan.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlanRequestDto {
    @Size(min = 3, max=20, message = "이름의 길이는 최소 3글자, 최대 20글자입니다.")
    private String planName;
    private List<Integer> contentIdList;

    @Builder
    public PlanRequestDto(String planName, List<Integer> contentIdList) {
        this.planName = planName;
        this.contentIdList = contentIdList;
    }

    @Override
    public String toString() {
        return "PlanRequestDto{" +
                "planName='" + planName + '\'' +
                ", contentIdList=" + contentIdList +
                '}';
    }
}
