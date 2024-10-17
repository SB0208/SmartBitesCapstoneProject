package com.library.backend.AI;

import org.junit.jupiter.api.Test;

class AIServiceTest {

    private AIService aiService = new AIService();

    @Test
    void testGenerateMealSuggestions() {

        String prompt ="healthy meal";
        String result =aiService.generateMealSuggestions(prompt);
       assert result != null;


    }
}