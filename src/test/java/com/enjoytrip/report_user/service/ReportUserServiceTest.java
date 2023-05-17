package com.enjoytrip.report_user.service;


import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.report_user.dto.ReportUserDto;
import com.enjoytrip.report_user.dto.ReportUserRegisterDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    static int[] memberIds = new int[5];
    
    @BeforeEach
    void beforeEach() throws SQLException {
        for (int i = 1; i <= 5; i++) {
            memberIds[i-1]= memberService.join(new RegisterDto(i+"test@test",i+"test",true,"test"));
        }
    }



    @Test
    void reportUserByReporterId() {

        List<ReportUserRegisterDto> reportUserRegisterDtoList = new ArrayList<>();
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[1],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[2],12,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[3],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[1],memberIds[0],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[1],1));

        try {
            for (ReportUserRegisterDto reportUserRegisterDto : reportUserRegisterDtoList) {
                reportUserService.register(reportUserRegisterDto);
            }
            Assertions.assertEquals(reportUserService.findReportUserByReporterId(memberIds[0]).size(),3);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void reportUserByTargetId() {
        List<ReportUserRegisterDto> reportUserRegisterDtoList = new ArrayList<>();
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[1],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[2],12,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[3],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[1],memberIds[0],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[1],1));

        try {
            for (ReportUserRegisterDto reportUserRegisterDto : reportUserRegisterDtoList) {
                reportUserService.register(reportUserRegisterDto);
            }
            Assertions.assertEquals(reportUserService.findReportUserByTargetId(memberIds[1]).size(),2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void reportUserByDoYn() {
        List<ReportUserRegisterDto> reportUserRegisterDtoList = new ArrayList<>();
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[1],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[2],12,1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[3],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[1],memberIds[0],1));
        reportUserRegisterDtoList.add(new ReportUserRegisterDto(memberIds[0],memberIds[1],1));
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
        ReportUserRegisterDto reportUserRegisterDto = new ReportUserRegisterDto(memberIds[0],memberIds[1],1);
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
        ReportUserRegisterDto reportUserRegisterDto = new ReportUserRegisterDto(memberIds[0],memberIds[1],1);
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