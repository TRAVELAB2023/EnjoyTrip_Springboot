package com.enjoytrip.report_user.controller;


import com.enjoytrip.report_user.dto.ReportUserDto;
import com.enjoytrip.report_user.dto.ReportUserRegisterDto;
import com.enjoytrip.report_user.service.ReportUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/report-user")
public class ReportUserController {

    private final ReportUserService reportUserService;

    public ReportUserController(ReportUserService reportUserService) {
        this.reportUserService = reportUserService;
    }

    @PostMapping("/report")
    public ResponseEntity reportUser(@RequestBody ReportUserRegisterDto reportUserRegisterDto) throws SQLException {
        reportUserService.register(reportUserRegisterDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
