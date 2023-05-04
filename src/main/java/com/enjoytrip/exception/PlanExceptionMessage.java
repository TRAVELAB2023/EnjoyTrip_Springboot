package com.enjoytrip.exception;

public enum PlanExceptionMessage {
    DATA_NOT_FOUND("계획을 찾을 수 없습니다."),
    NO_AUTH("권한이 없습니다.");


    PlanExceptionMessage(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }
}
