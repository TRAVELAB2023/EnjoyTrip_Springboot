package com.enjoytrip.exception.custom_exception;

import com.enjoytrip.exception.message.FileExceptionMessage;

import java.io.IOException;

public class FileException extends IOException {

    public FileException(FileExceptionMessage msg) {
        super(msg.getMsg());
    }
}
