package com.enjoytrip.exception.message;

public enum MemberExceptionMessage {
    DUPLICATED_EMAIL("이메일이 중복되었습니다."),
    DUPLICATED_NICKNAME("닉네임이 중복되었습니다."),
    DUPLICATED_EMAIL_AND_NICKNAME("이메일과 닉네임이 중복되었습니다."),
    WRONG_ID_OR_PASSWORD("아이디 혹은 비밀번호가 틀렸습니다."),
    WRONG_PASSWORD("비밀번호가 틀렸습니다.");

    MemberExceptionMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    private String msg;
}
