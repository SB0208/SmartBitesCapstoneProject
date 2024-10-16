package com.library.backend.AI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class AIController {

    @Autowired
    private AIService aiService;

    @GetMapping("/api/suggest-meal")
    public String suggestMeal(@RequestParam String input){
        return aiService.generateMealSuggestions(input);
    }
}
