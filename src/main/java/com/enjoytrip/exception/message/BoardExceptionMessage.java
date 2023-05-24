package com.enjoytrip.exception.message;

public enum BoardExceptionMessage {
    WRONG_PAGE("잘못된 페이지입니다."),
    NO_PERMISSION("권한이 존재하지 않습니다");


    BoardExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
