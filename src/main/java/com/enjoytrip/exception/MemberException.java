package com.enjoytrip.exception;

public class MemberException extends RuntimeException {
    public MemberException(MemberExceptionMessage memberExceptionMessage) {
        super(memberExceptionMessage.getMsg());
    }
}

