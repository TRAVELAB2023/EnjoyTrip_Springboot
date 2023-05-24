package com.enjoytrip.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatReseponseDto {
    private String id;
    private String object;
    private String model;
    private Usage usage;   //
    private Choices[] choices;


    public String getMessage(){
        return this.choices[0].getMessage().getContent();
    }
    @Getter
    @NoArgsConstructor
    private class Usage{
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }

    @Getter
    @NoArgsConstructor
    private class Choices {
        private Message message;
        private String finish_reason;
        private int index;


        @Getter
        @NoArgsConstructor
        private class Message {
            private String role;
            private String content; // 메시지 내용
        }
    }
}
