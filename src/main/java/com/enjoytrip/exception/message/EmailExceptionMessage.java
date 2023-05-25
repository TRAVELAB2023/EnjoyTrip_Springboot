package com.enjoytrip.exception.message;

public enum EmailExceptionMessage {
    DATA_NOT_FOUND("정보를 찾을 수 없습니다.");


    EmailExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
