package com.enjoytrip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "attraction_view")
@NoArgsConstructor
public class Attraction {
    @Id
    @Column(name = "content_id")
    private int contentId;

    @Column(name="content_type_id")
    private int contentTypeId;

    @Column(name="title")
    private String title;

    @Column(name="addr1")
    private String addr1;

    @Column(name="first_image")
    private String firstImage;

    @Column(name="sido_code")
    private int sidoCode;

    @Column(name="gugun_code")
    private int gugunCode;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @Column(name="overview")
    private String overview;

    @Builder
    public Attraction(int contentId, int contentTypeId, String title, String addr1, String firstImage, int sidoCode, int gugunCode, double latitude, double longitude, String overview) {
        this.contentId = contentId;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.firstImage = firstImage;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "contentId=" + contentId +
                ", contentTypeId=" + contentTypeId +
                ", title='" + title + '\'' +
                ", addr1='" + addr1 + '\'' +
                ", firstImage='" + firstImage + '\'' +
                ", sidoCode=" + sidoCode +
                ", gugunCode=" + gugunCode +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", overview='" + overview + '\'' +
                '}';
    }
}
