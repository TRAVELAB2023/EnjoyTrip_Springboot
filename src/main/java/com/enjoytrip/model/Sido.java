package com.enjoytrip.model;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity(name="sido")
public class Sido {
    @Id
    @Column(name = "sido_code", nullable = false)
    private int sidoCode;

    @Column(name="sido_name", nullable=false)
    private String sidoName;

    @OneToMany
    @JoinColumn(name="sido_code")
    private List<Gugun> gugunList;

    @Builder
    public Sido(int sidoCode, String sidoName){
        this.sidoCode=sidoCode;
        this.sidoName=sidoName;
    }

    public Sido() {

    }
}
