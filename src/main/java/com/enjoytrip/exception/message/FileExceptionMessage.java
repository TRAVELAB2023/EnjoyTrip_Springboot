package com.enjoytrip.exception.message;

public enum FileExceptionMessage {
    DATA_NOT_FOUND("파일을 찾을 수 없습니다."),
    WRONG_CONTENT_TYPE("파일이 잘못된 타입입니다. 가능한 타입(png,jpg,jpeg)"),
    TOO_BIG_SIZE("파일 사이즈가 너무 큽니다.(1MB 미만만 가능)");

    FileExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
