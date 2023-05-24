package com.enjoytrip.chat.dto;

import lombok.Getter;

@Getter
public class ChatMessage {
    private String message;  // 질문
    private String sidoName;
    private String gugunName;
    private String contentTypeName;

    private ChatMessage(String message, String sidoName, String gugunName, String contentTypeName) {
        this.message = message;
        this.sidoName = sidoName;
        this.gugunName = gugunName;
        this.contentTypeName = contentTypeName;
    }

    public static ChatMessage parse(String sidoName, String gugunName, String contentTypeName){
        StringBuilder sb=new StringBuilder();
        sb.append(sidoName).append(" ").append(gugunName).append(" ").append(contentTypeName).append(" 중에서 5개만 추천해줘");
        return new ChatMessage(sb.toString(),sidoName,gugunName,contentTypeName);
    }

}
