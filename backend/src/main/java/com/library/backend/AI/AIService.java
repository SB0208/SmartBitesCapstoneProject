package com.library.backend.AI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service

public class AIService {
    private static final String OPENAI_API_URL = "https://api.openapi.models.com/2.0/";
    private static final String API_KEY = "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";


    public String generateMealSuggestions(String prompt){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        String body = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
}
