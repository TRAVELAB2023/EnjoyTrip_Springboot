package com.enjoytrip.exception;

import com.enjoytrip.exception.custom_exception.MemberException;
import com.enjoytrip.exception.custom_exception.RoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionAdvise {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity SqlExceptionHandle() {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<String> MemberExceptionHandle(MemberException memberException) {
        return new ResponseEntity(memberException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<String> RoleExceptionHandle(MemberException memberException) {
        return new ResponseEntity(memberException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
