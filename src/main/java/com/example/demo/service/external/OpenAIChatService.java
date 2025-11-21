package com.example.demo.service.external;

import com.example.demo.service.AIAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenAIChatService implements AIAgent {

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.base-url}")
    private String baseUrl;

    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate;

    public OpenAIChatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String askQuestion(String message) {

        String url = baseUrl + "/chat/completions";

        ChatRequest request = new ChatRequest(
                model,
                List.of(new Message("user", message))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatResponse> response =
                restTemplate.postForEntity(url, entity, ChatResponse.class);

        if (response.getBody() == null ||
                response.getBody().choices == null ||
                response.getBody().choices.isEmpty()) {
            return "Boş cevap döndü.";
        }

        return response.getBody().choices.get(0).message.content;
    }

    // ==== DTO'lar ====

    public static class ChatRequest {
        public String model;
        public List<Message> messages;

        public ChatRequest(String model, List<Message> messages) {
            this.model = model;
            this.messages = messages;
        }
    }

    public static class Message {
        public String role;
        public String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    public static class ChatResponse {
        public List<Choice> choices;
    }

    public static class Choice {
        public Message message;
        public int index;
        public String finish_reason;
    }
}
