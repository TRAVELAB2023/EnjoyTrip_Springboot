package com.enjoytrip.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attraction_view")
@NoArgsConstructor
public class Attraction {
    @Id
    @Column(name="content_id", nullable = false)
    private int contentId;

    @Column(name="content_type_id", nullable = false)
    private int contentTypeId;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name = "addr1")
    private String addr1;

    @Column(name="first_image")
    private String firstImage;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @Column(name="overview")
    private String overview;
}
