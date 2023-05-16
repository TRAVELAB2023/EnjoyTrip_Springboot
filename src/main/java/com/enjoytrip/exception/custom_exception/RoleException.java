package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.RoleExceptionMessage;

public class RoleException extends RuntimeException{
    public RoleException(RoleExceptionMessage roleExceptionMessage){
        super(roleExceptionMessage.getMsg());
    }
}
