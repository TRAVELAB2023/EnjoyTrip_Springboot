package com.enjoytrip.report_user.service;

import com.enjoytrip.model.ReportUser;
import com.enjoytrip.report_user.dto.ReportUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Transactional
class ReportUserServiceTest {

    @Autowired
    ReportUserService reportUserService;

    @Test
    void reportUserByReporterId() {
        List<ReportUserDto> reportUserDtoList = new ArrayList<>();
        reportUserDtoList.add(new ReportUserDto(17,18,1));
        reportUserDtoList.add(new ReportUserDto(19,12,1));
        reportUserDtoList.add(new ReportUserDto(17,20,1));
        reportUserDtoList.add(new ReportUserDto(18,17,1));
        reportUserDtoList.add(new ReportUserDto(17,18,1));

        try {
            for (ReportUserDto reportUserDto : reportUserDtoList) {
                reportUserService.register(reportUserDto);
            }
            Assertions.assertEquals(reportUserService.ReportUserByReporterId(17).size(),3);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void reportUserByTargetId() {
        List<ReportUserDto> reportUserDtoList = new ArrayList<>();
        reportUserDtoList.add(new ReportUserDto(17,18,1));
        reportUserDtoList.add(new ReportUserDto(19,12,1));
        reportUserDtoList.add(new ReportUserDto(17,20,1));
        reportUserDtoList.add(new ReportUserDto(18,17,1));
        reportUserDtoList.add(new ReportUserDto(17,18,1));

        try {
            for (ReportUserDto reportUserDto : reportUserDtoList) {
                reportUserService.register(reportUserDto);
            }
            Assertions.assertEquals(reportUserService.ReportUserByTargetId(18).size(),2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void reportUserByDoYn() {
        List<ReportUserDto> reportUserDtoList = new ArrayList<>();
        reportUserDtoList.add(new ReportUserDto(17,18,1));
        reportUserDtoList.add(new ReportUserDto(19,12,1));
        reportUserDtoList.add(new ReportUserDto(17,20,1));
        reportUserDtoList.add(new ReportUserDto(18,17,1));
        reportUserDtoList.add(new ReportUserDto(17,18,1));
        int id = 0;
        try {
            for (ReportUserDto reportUserDto : reportUserDtoList) {
                id = reportUserService.register(reportUserDto);
            }
            ReportUserDto reportUserDto = reportUserService.find(id);
            reportUserDto.modifyDoYn(true);
            reportUserService.update(reportUserDto);
            Assertions.assertEquals(reportUserService.ReportUserByDoYn(true).size(), 1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void register() {
        ReportUserDto reportUserDto = new ReportUserDto(17,18,1);
        try {
            int id =  reportUserService.register(reportUserDto);
            ReportUserDto reportUserDto2= reportUserService.find(id);
            Assertions.assertEquals(reportUserDto.getReporterMemberId(),reportUserDto2.getReporterMemberId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        ReportUserDto reportUserDto = new ReportUserDto(17,18,1);
        try {
            int id =  reportUserService.register(reportUserDto);
            reportUserDto = reportUserService.find(id);
            reportUserDto.modifyDoYn(true);
            reportUserService.update(reportUserDto);
            reportUserDto = reportUserService.find(id);
            Assertions.assertEquals(reportUserDto.isDoYn(), true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}