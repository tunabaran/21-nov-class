//package com.example.demo.service.external;
//
//import com.example.demo.service.AIAgent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class ChatGptService implements AIAgent {
//
//    private final String apiUrl = "https://api.openai.com/v1/completions";
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Override
//    public String askQuestion(String question) {
//        String requestPayload = String.format("{\"model\": \"text-davinci-003\", \"prompt\": \"%s\", \"max_tokens\": 150}", question);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(requestPayload, headers);
//
//        ResponseEntity<String> response =
//                restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
//
//        return response.getBody();
//    }
//}