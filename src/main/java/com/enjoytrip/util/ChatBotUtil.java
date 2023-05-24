package com.enjoytrip.util;

import com.enjoytrip.chat.dto.ChatReseponseDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;


@Component
public class ChatBotUtil {
    private final String baseUrl="https://api.openai.com/v1/chat/completions";
    @Value("${chatGPT_key}")
    private String chatGPTKey;


    private RestTemplate restTemplate;

    public ChatBotUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String chat(String msg) throws IOException {

        // 보낼 데이터 세팅
        String json="{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": \"너는 한국 관광지 추천 가이드야. 한국 관광지와 관련된 질문에만 대답해줘\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": \"너는 질문이 만약 한국 관광지와 연관이 있다면 글의 첫머리를 '제가 추천하는 여행지는 '으로 시작하고 답변을 관광지 관련 설명 없이 관광지 명칭만 나열하고  '입니다'로 대답해줘\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \""+msg+"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        RequestEntity<String> requestEntity= RequestEntity
                .post(URI.create(baseUrl))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+chatGPTKey)
                .body(json);
        ResponseEntity<String> responseEntity= restTemplate.exchange(requestEntity, String.class);
        Gson gson=new Gson();
        ChatReseponseDto chatReseponseDto=gson.fromJson(responseEntity.getBody(),ChatReseponseDto.class);
        return chatReseponseDto.getMessage();
    }

}
