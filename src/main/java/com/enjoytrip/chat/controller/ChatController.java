package com.enjoytrip.chat.controller;

import com.enjoytrip.chat.dto.ChatMessage;
import com.enjoytrip.util.ChatBotUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatBotUtil chatBotUtil;

    public ChatController(ChatBotUtil chatBotUtil) {
        this.chatBotUtil = chatBotUtil;
    }

    @GetMapping("")
    public ResponseEntity<String> chat(@RequestParam(required = false,defaultValue = "") String sidoName,
                                       @RequestParam(required = false,defaultValue = "") String gugunName,
                                       @RequestParam(required = false,defaultValue = "") String contentTypeName) throws IOException {
        ChatMessage chatMessage=ChatMessage.parse(sidoName,gugunName,contentTypeName);
        String msg=chatBotUtil.chat(chatMessage);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
