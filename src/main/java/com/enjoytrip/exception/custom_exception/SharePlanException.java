package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.SharePlanExceptionMessage;

public class SharePlanException extends RuntimeException{
    public SharePlanException(SharePlanExceptionMessage message) {
        super(message.getMsg());
    }
}
