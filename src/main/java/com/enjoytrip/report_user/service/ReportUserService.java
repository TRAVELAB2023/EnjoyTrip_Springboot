package com.enjoytrip.report_user.service;

import com.enjoytrip.model.ReportUser;
import com.enjoytrip.report_user.dto.ReportUserDto;
import com.enjoytrip.report_user.dto.ReportUserRegisterDto;

import java.sql.SQLException;
import java.util.List;

public interface ReportUserService {
    ReportUserDto find(int reportId) throws SQLException;
    List<ReportUserDto> findReportUserByReporterId(int reporterId) throws SQLException;
    List<ReportUserDto> findReportUserByTargetId(int targetId) throws SQLException;
    List<ReportUserDto> findReportUserByDoYn(boolean doYn) throws SQLException;
    int register(ReportUserRegisterDto reportUserRegisterDto) throws SQLException;
    int update(ReportUserDto reportUserDto) throws SQLException;
}
