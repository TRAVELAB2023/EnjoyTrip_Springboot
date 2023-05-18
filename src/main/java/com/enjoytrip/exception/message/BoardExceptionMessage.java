package com.enjoytrip.exception.message;

public enum BoardExceptionMessage {
    WRONG_PAGE("잘못된 페이지입니다.");


    BoardExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
