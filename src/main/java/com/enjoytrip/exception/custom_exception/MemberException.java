package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.MemberExceptionMessage;

public class MemberException extends RuntimeException {
    public MemberException(MemberExceptionMessage memberExceptionMessage) {
        super(memberExceptionMessage.getMsg());
    }
}

