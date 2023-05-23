package com.enjoytrip.exception.message;

public enum SharePlanExceptionMessage {

    DATA_NOT_FOUND("데이터를 찾을 수 없습니다.");


    SharePlanExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
