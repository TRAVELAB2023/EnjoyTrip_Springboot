package com.enjoytrip.report_user.dto;

import lombok.Getter;

@Getter
public class ReportUserRegisterDto {
    int reporterId;
    int targetId;
    int reportType;

    public ReportUserRegisterDto(int reporterId, int targetId, int reportType) {
        this.reporterId = reporterId;
        this.targetId = targetId;
        this.reportType = reportType;
    }
}
