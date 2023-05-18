package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.BoardExceptionMessage;
import com.enjoytrip.exception.message.MemberExceptionMessage;

public class BoardException extends RuntimeException {
    public BoardException(BoardExceptionMessage boardExceptionMessage) {
        super(boardExceptionMessage.getMsg());
    }
}
