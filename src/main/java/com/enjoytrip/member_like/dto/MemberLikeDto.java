package com.enjoytrip.member_like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberLikeDto {
    @NotBlank(message = "값이 입력되어 있지 않습니다.")
    private Integer attractionId;

    public MemberLikeDto(Integer attractionId) {
        this.attractionId = attractionId;
    }

    @Override
    public String toString() {
        return "MemberLikeDto{" +
                "attractionId=" + attractionId +
                '}';
    }
}
