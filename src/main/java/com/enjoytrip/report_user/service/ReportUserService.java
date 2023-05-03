package com.enjoytrip.report_user.service;

import com.enjoytrip.model.ReportUser;
import com.enjoytrip.report_user.dto.ReportUserDto;

import java.sql.SQLException;
import java.util.List;

public interface ReportUserService {
    ReportUserDto find(int reportId) throws SQLException;
    List<ReportUserDto> ReportUserByReporterId(int reporterId) throws SQLException;
    List<ReportUserDto> ReportUserByTargetId(int targetId) throws SQLException;
    List<ReportUserDto> ReportUserByDoYn(boolean doYn) throws SQLException;
    int register(ReportUserDto reportUserDto) throws SQLException;
    int update(ReportUserDto reportUserDto) throws SQLException;
}
