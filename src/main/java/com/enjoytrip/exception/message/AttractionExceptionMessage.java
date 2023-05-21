package com.enjoytrip.exception.message;

public enum AttractionExceptionMessage {
    DATA_NOT_FOUND("관광지가 존재하지 않습니다.");


    AttractionExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
