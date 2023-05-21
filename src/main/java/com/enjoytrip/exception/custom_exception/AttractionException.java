package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.AttractionExceptionMessage;

public class AttractionException extends RuntimeException {
    public AttractionException(AttractionExceptionMessage msg) {
        super(msg.getMsg());

    }
}
