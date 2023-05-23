package com.enjoytrip.share_plan.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SharePlanRequestDto {

    @Min(value=0, message = "값이 너무 작습니다.")
    @Max(value=Integer.MAX_VALUE, message = "값이 너무 작습니다.")
    @NotNull(message = "값이 입력되어있지 않습니다.")
    private Integer planId;
    @Future(message = "기간이 지났습니다. 기간을 다시 설정해주세요.")
    @NotNull(message = "값이 입력되어있지 않습니다.")
    @JsonSerialize(using= LocalDateSerializer.class)
    private LocalDate haltDate;

    @Builder
    public SharePlanRequestDto(int planId, LocalDate haltDate) {
        this.planId = planId;
        this.haltDate = haltDate;
    }
}
