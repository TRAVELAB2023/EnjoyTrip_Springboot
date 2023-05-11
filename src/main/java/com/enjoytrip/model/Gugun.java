package com.enjoytrip.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity(name = "gugun")
public class Gugun {

    @Id
    @Column(name="gugun_code", nullable = false)
    private int gugunCode;

    @Column(name = "gugun_name", nullable=false)
    private String gugunName;


    public Gugun() {
    }

    @Builder
    public Gugun(int gugunCode, String gugunName) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
    }

    @Override
    public String toString() {
        return "Gugun{" +
                "gugunCode=" + gugunCode +
                ", gugunName='" + gugunName + '\'' +
                '}';
    }
}
