package com.enjoytrip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "report_user")
public class ReportUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    int reportId;

    @Column(name = "reporter_member_id")
    int reporterMemberId;
    @Column(name = "target_member_id")
    int targetMemberId;
    @Column(name = "report_type")
    int reportType;
    @Column(name = "do_yn")
    boolean doYn;
    @Column(name = "delete_yn")
    boolean deleteYn;

    @Builder
    public ReportUser(int reportId, int reporterMemberId, int targetMemberId, int reportType, boolean doYn, boolean deleteYn) {
        this.reportId = reportId;
        this.reporterMemberId = reporterMemberId;
        this.targetMemberId = targetMemberId;
        this.reportType = reportType;
        this.doYn = doYn;
        this.deleteYn = deleteYn;
    }
}
