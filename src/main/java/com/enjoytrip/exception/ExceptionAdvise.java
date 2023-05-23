package com.enjoytrip.exception;

import com.enjoytrip.exception.custom_exception.*;
import com.enjoytrip.exception.message.FileExceptionMessage;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionAdvise {
    @ExceptionHandler(Exception.class)
    public ResponseEntity ExceptionHandle(Exception e)
    {
        e.printStackTrace();
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity BoardExceptionHandle(BoardException boardException) {
        return new ResponseEntity(boardException.getMessage(), HttpStatus.UNAUTHORIZED);
    }
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

    @ExceptionHandler
    public ResponseEntity<String> MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException exception){
        return new ResponseEntity(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<String> PlanExceptionHandle(PlanException planException){
        return new ResponseEntity(planException.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<String> FileExceptionHandle(FileException fileException){
        return new ResponseEntity(fileException.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> AttractionExceptionHandle(AttractionException attractionException){
        return new ResponseEntity<>(attractionException.getMessage(),HttpStatus.BAD_REQUEST);
    }
    /**
     * maxFileSize 초과시 발생
     * @param fileSizeLimitExceededException
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<String> FileSizeLimitExceededExceptionHandle(FileSizeLimitExceededException fileSizeLimitExceededException){
        return new ResponseEntity<String>(FileExceptionMessage.TOO_BIG_SIZE.getMsg(),HttpStatus.BAD_REQUEST);
    }

    /**
     * maxRequestSize 초과 시 발생
     * @param sizeLimitExceededException
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<String> SizeLimitExceededExceptionHandle(SizeLimitExceededException sizeLimitExceededException){
        return new ResponseEntity<String>(FileExceptionMessage.TOO_BIG_SIZE.getMsg(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> AccessTokenExpiration(UnAuthorizedException unAuthorizedException) {
        return new ResponseEntity<>("액세스 토큰 만료", HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler
    public ResponseEntity<String> SharePlanExceptionHandle(SharePlanException sharePlanException){
        return new ResponseEntity<>(sharePlanException.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
