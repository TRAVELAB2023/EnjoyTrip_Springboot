package com.enjoytrip.exception.message;

public enum WrongPageExceptionMessage {
    WRONG_PAGE("잘못된 페이지입니다.");


    WrongPageExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
