package com.enjoytrip.exception;

public class PlanException extends RuntimeException{

    public PlanException(PlanExceptionMessage planExceptionMessage){
        super(planExceptionMessage.getMsg());
    }
}
