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
        /* msg 샘플 내일 해야함
        {
  "id": "chatcmpl-7JQ0SbGLOjCxz5HSlYUzn1RmH2zQP",
  "object": "chat.completion",
  "created": 1684862556,
  "model": "gpt-3.5-turbo-0301",
  "usage": {
    "prompt_tokens": 50,
    "completion_tokens": 18,
    "total_tokens": 68
  },
  "choices": [
    {
      "message": {
        "role": "assistant",
        "content": "네, 무엇을 도와드릴까요?"
      },
      "finish_reason": "stop",
      "index": 0
    }
  ]
}
         */

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
