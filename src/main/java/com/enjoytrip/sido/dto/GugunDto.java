package com.enjoytrip.sido.dto;

import com.enjoytrip.model.Gugun;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class GugunDto {

    private int gugunCode;

    private String gugunName;

    public GugunDto() {
    }
    public GugunDto(Gugun gugun) {
        this.gugunCode = gugun.getGugunCode();
        this.gugunName = gugun.getGugunName();
    }
}
