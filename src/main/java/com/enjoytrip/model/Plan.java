package com.enjoytrip.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "plan")
@NoArgsConstructor
public class Plan {
    @Id
    @Column(name="plan_id")
    private int planId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="member_id")
    private int memberId;

    @Column(name="register_time")
    private LocalDateTime register_time;
}
