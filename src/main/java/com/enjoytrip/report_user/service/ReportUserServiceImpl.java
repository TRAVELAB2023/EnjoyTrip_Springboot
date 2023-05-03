package com.enjoytrip.report_user.service;

import com.enjoytrip.model.ReportUser;
import com.enjoytrip.report_user.dto.ReportUserDto;
import com.enjoytrip.report_user.repository.ReportUserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportUserServiceImpl implements ReportUserService {
    private final ReportUserRepository reportUserRepository;

    public ReportUserServiceImpl(ReportUserRepository reportUserRepository) {
        this.reportUserRepository = reportUserRepository;
    }

    @Override
    public ReportUserDto find(int reportId) throws SQLException {
        return new ReportUserDto(reportUserRepository.findByReportId(reportId));

    }

    private static List<ReportUserDto> convertModelListToDtoList(List<ReportUser> reportUsers) {
        List<ReportUserDto> reportUserDtoList = new ArrayList<>();
        for (ReportUser reportUser : reportUsers) {
            reportUserDtoList.add(new ReportUserDto(reportUser));
        }
        return reportUserDtoList;
    }

    @Override
    public List<ReportUserDto> ReportUserByReporterId(int reporterId) throws SQLException {
        List<ReportUser> reportUsers = reportUserRepository.findReportUsersByReporterMemberId(reporterId);
        return convertModelListToDtoList(reportUsers);
    }


    @Override
    public List<ReportUserDto> ReportUserByTargetId(int targetId) throws SQLException {
        List<ReportUser> reportUsers = reportUserRepository.findReportUsersByTargetMemberId(targetId);
        return convertModelListToDtoList(reportUsers);
    }

    @Override
    public List<ReportUserDto> ReportUserByDoYn(boolean doYn) throws SQLException {
        List<ReportUser> reportUsers = reportUserRepository.findReportUsersByDoYn(doYn);
        return convertModelListToDtoList(reportUsers);
    }

    @Override
    public int register(ReportUserDto reportUserDto) throws SQLException {
        ReportUser reportUser = ReportUser.builder()
                .reporterMemberId(reportUserDto.getReporterMemberId())
                .targetMemberId(reportUserDto.getTargetMemberId())
                .reportType(reportUserDto.getReportType()).
                build();
        return reportUserRepository.save(reportUser).getReportId();
    }

    @Override
    public int update(ReportUserDto reportUserDto) throws SQLException {
        return 0;
    }
}
