package com.openai.ChatBotApplication.controller;

import com.openai.ChatBotApplication.dao.ChatGptRequest;
import com.openai.ChatBotApplication.dao.ChatGptResponse;
import com.openai.ChatBotApplication.dao.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatBotController {

    @PostMapping("/chat")
    public String getChatGptResponse(@RequestBody String query) {

        ChatGptRequest request = new ChatGptRequest();

        Message message = new Message();

        message.setContent(query);

        message.setRole("user");

        List<Message> messages = new ArrayList<>();

        messages.add(message);

        request.setMessages(messages);

        request.setModel("gpt-3.5-turbo");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "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");

        HttpEntity<ChatGptRequest> entity = new HttpEntity<ChatGptRequest>(request , headers);

        ResponseEntity<ChatGptResponse> chatGptResponse =  restTemplate.exchange("https://api.openai.com/v1/chat/completions" , HttpMethod.POST , entity , ChatGptResponse.class);

        return chatGptResponse.getBody().getChoices().get(0).getMessage().getContent();
    }
}
