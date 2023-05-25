package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.EmailExceptionMessage;

public class EmailException extends RuntimeException{

    public EmailException(EmailExceptionMessage message) {
        super(message.getMsg());
    }
}
