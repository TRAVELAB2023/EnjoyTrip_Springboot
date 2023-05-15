package com.enjoytrip.report_user.dto;

import com.enjoytrip.model.ReportUser;
import lombok.Getter;

@Getter
public class ReportUserDto {
    private int reportId;
    private int reporterMemberId;
    private int targetMemberId;
    private int reportType;
    private boolean doYn;
    private boolean deleteYn;

    public ReportUserDto(ReportUser reportUser) {
        this.reportId = reportUser.getReportId();
        this.reporterMemberId = reportUser.getReporterMemberId();
        this.targetMemberId = reportUser.getTargetMemberId();
        this.reportType = reportUser.getReportType();
        this.doYn = reportUser.isDoYn();
        this.deleteYn = reportUser.isDeleteYn();
    }


    public void modifyDoYn(boolean doYn) {
        this.doYn = doYn;
    }
}
