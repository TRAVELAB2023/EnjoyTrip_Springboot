package com.enjoytrip.report_user.service;

import com.enjoytrip.report_user.dto.ReportUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.SQLException;


@SpringBootTest
@Transactional
class ReportUserServiceTest {

    @Autowired
    ReportUserService reportUserService;

    @Test
    void find() {
    }

    @Test
    void reportUserByReporterId() {
    }

    @Test
    void reportUserByTargetId() {
    }
    @Test
    void reportUserByDoYn() {
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
    }


}