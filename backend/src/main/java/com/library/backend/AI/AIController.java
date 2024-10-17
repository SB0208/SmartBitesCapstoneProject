package com.library.backend.AI;

import com.library.backend.AI.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AIController {


    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/api/suggest-meal")
    public String suggestMeal(@RequestParam String input){
        return aiService.generateMealSuggestions(input);
    }
}
