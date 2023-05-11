package com.enjoytrip.report_user.service;


import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.report_user.dto.ReportUserDto;
import com.enjoytrip.report_user.dto.ReportUserRegisterDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    @Autowired
    MemberService memberService;



    @Test
    void reportUserByReporterId() {

        List<ReportUserRegisterDto> reportUserRegisterDtoList = new ArrayList<>();
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,28,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(29,12,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,30,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(28,27,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,28,1));

        try {
            for (ReportUserRegisterDto reportUserRegisterDto : reportUserRegisterDtoList) {
                reportUserService.register(reportUserRegisterDto);
            }
            Assertions.assertEquals(reportUserService.findReportUserByReporterId(27).size(),3);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void reportUserByTargetId() {
        List<ReportUserRegisterDto> reportUserRegisterDtoList = new ArrayList<>();
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,28,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(29,12,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,30,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(28,27,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,28,1));

        try {
            for (ReportUserRegisterDto reportUserRegisterDto : reportUserRegisterDtoList) {
                reportUserService.register(reportUserRegisterDto);
            }
            Assertions.assertEquals(reportUserService.findReportUserByTargetId(28).size(),2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void reportUserByDoYn() {
        List<ReportUserRegisterDto> reportUserRegisterDtoList = new ArrayList<>();
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,28,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(29,12,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,30,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(28,27,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(27,28,1));
        int id = 0;
        try {
            for (ReportUserRegisterDto reportUserRegisterDto : reportUserRegisterDtoList) {
                 id= reportUserService.register(reportUserRegisterDto);
            }
            ReportUserDto reportUserDto = reportUserService.find(id);
            reportUserDto.modifyDoYn(true);
            reportUserService.update(reportUserDto);
            Assertions.assertEquals(reportUserService.findReportUserByDoYn(true).size(), 1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void register() {
        ReportUserRegisterDto reportUserRegisterDto = new ReportUserRegisterDto(27,28,1);
        try {
            int id =  reportUserService.register(reportUserRegisterDto);
            ReportUserDto reportUserDto2= reportUserService.find(id);
            Assertions.assertEquals(reportUserRegisterDto.getReporterId(),reportUserDto2.getReporterMemberId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        ReportUserRegisterDto reportUserRegisterDto = new ReportUserRegisterDto(27,28,1);
        try {
            int id =  reportUserService.register(reportUserRegisterDto);
            ReportUserDto reportUserDto = reportUserService.find(id);
            reportUserDto.modifyDoYn(true);
            reportUserService.update(reportUserDto);
            reportUserDto = reportUserService.find(id);
            Assertions.assertEquals(reportUserDto.isDoYn(), true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}