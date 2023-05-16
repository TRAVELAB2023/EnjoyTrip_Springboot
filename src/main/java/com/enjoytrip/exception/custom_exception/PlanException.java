package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.PlanExceptionMessage;

public class PlanException extends RuntimeException{

    public PlanException(PlanExceptionMessage planExceptionMessage){
        super(planExceptionMessage.getMsg());
    }
}
