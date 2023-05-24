package com.enjoytrip.chat.controller;

import com.enjoytrip.chat.dto.ChatDto;
import com.enjoytrip.util.ChatBotUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatBotUtil chatBotUtil;

    public ChatController(ChatBotUtil chatBotUtil) {
        this.chatBotUtil = chatBotUtil;
    }

    @PostMapping("")
    public ResponseEntity<String> chat(@RequestBody @Valid ChatDto chatDto) throws IOException {
        String msg=chatBotUtil.chat(chatDto.getMessage());

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
