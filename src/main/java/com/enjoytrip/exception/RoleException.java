package com.enjoytrip.exception;

public class RoleException extends RuntimeException{
    public RoleException(RoleExceptionMessage roleExceptionMessage){
        super(roleExceptionMessage.getMsg());
    }
}
