package com.enjoytrip.report_user.repository;

import com.enjoytrip.model.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportUserRepository extends JpaRepository<ReportUser, Integer> {
    ReportUser findByReportId(int reportId);

    List<ReportUser> findReportUsersByReporterMemberId(int reporterMemberId);
    List<ReportUser> findReportUsersByTargetMemberId(int targetMemberId);
    List<ReportUser> findReportUsersByDoYn(boolean doYn);
}
