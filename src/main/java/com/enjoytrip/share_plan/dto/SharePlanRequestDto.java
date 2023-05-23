package com.enjoytrip.share_plan.dto;

import com.enjoytrip.model.SharePlan;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SharePlanRequestDto {

    @Positive(message= "잘못된 값입니다.")
    @NotBlank(message = "값이 입력되어있지 않습니다.")
    private Integer planId;
    @Future(message = "기간이 지났습니다. 기간을 다시 설정해주세요.")
    @NotBlank(message = "값이 입력되어있지 않습니다.")
    @JsonSerialize(using= LocalDateSerializer.class)
    private LocalDate haltDate;

    @Builder
    public SharePlanRequestDto(Integer planId, LocalDate haltDate) {
        this.planId = planId;
        this.haltDate = haltDate;
    }
}
