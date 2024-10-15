package com.library.backend.controller;
import com.library.backend.service.NutritionAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutrition")

public class NutriController {

    @Autowired
    private NutritionAIService nutritionAIService;

    @GetMapping("/recommendation")
    public String getMealRecommendation(@RequestParam int calorieGoal) {
        return nutritionAIService.generateMealRecommendation(calorieGoal);
    }
}
