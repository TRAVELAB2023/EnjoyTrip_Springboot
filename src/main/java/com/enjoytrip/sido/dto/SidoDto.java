package com.enjoytrip.sido.dto;

import com.enjoytrip.model.Sido;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SidoDto {

    private int sidoCode;
    private String sidoName;

    public SidoDto() {
    }

    public SidoDto(Sido sido){
        this.sidoCode=sido.getSidoCode();
        this.sidoName=sido.getSidoName();
    }


}
