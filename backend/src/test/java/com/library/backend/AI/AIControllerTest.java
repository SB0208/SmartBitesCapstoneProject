package com.library.backend.AI;

import org.junit.jupiter.api.Test;
import com.library.backend.AI.AIService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AIController.class)

class AIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AIService service;
    @Autowired
    private NutritionAIService nutritionAIService;

    @Test
    void suggestMeal() throws Exception {
        when (nutritionAIService.generateMealRecommendation(1500)).thenReturn("Salad");
        mockMvc.perform(get("/api/v1/meals/healthy"))
                .andExpect(status().isOk());



    }
}