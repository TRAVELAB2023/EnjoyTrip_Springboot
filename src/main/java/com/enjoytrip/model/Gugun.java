package com.enjoytrip.model;

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


    @Override
    public String toString() {
        return "Gugun{" +
                "gugunCode=" + gugunCode +
                ", gugunName='" + gugunName + '\'' +
                '}';
    }
}
