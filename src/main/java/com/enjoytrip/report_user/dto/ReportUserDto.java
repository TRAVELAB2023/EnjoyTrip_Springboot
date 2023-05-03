package com.enjoytrip.report_user.dto;

import com.enjoytrip.model.ReportUser;
import lombok.Getter;

@Getter
public class ReportUserDto {
    int reportId;
    int reporterMemberId;
    int targetMemberId;
    int reportType;
    boolean doYn;
    boolean deleteYn;

    public ReportUserDto(ReportUser reportUser) {
        this.reportId = reportUser.getReportId();
        this.reporterMemberId = reportUser.getReporterMemberId();
        this.targetMemberId = reportUser.getTargetMemberId();
        this.reportType = reportUser.getReportType();
        this.doYn = reportUser.isDoYn();
        this.deleteYn = reportUser.isDeleteYn();
    }

    public ReportUserDto(int reporterMemberId, int targetMemberId, int reportType) {
        this.reporterMemberId = reporterMemberId;
        this.targetMemberId = targetMemberId;
        this.reportType = reportType;
    }
}
