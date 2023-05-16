package com.enjoytrip.exception.message;

public enum RoleExceptionMessage {
    NO_AUTH("권한이 없습니다.");


    RoleExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
