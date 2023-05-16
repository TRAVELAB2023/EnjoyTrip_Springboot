package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.WrongPageExceptionMessage;

public class WrongPageException extends RuntimeException {
    public WrongPageException(WrongPageExceptionMessage wrongPageExceptionMessage) {
        super(wrongPageExceptionMessage.getMsg());
    }
}
