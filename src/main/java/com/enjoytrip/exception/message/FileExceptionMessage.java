package com.enjoytrip.exception.message;

public enum FileExceptionMessage {
    DATA_NOT_FOUND("파일을 찾을 수 없습니다.");

    FileExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
