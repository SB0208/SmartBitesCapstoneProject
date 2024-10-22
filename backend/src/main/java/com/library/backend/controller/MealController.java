package com.library.backend.controller;
import com.library.backend.model.Meal;
import com.library.backend.service.MealService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private MealService mealService;
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping()
    public List<Meal> getAllMeals() {
        List<Meal> meals = mealService.getAllMeals();
        return meals;
    }

    @PostMapping()
    public ResponseEntity<String> saveMeal(@RequestBody Meal myMeal) {
         mealService.saveMeal(myMeal);
        return ResponseEntity.status(HttpStatus.CREATED).body("Meal saved successfully");
    }


}