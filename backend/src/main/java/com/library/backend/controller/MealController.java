package com.library.backend.controller;
import com.library.backend.model.Meal;
import com.library.backend.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("/log")
    public ResponseEntity<String> logMeal(@RequestBody Meal meal) {
        mealService.logMeal(meal);
        return ResponseEntity.ok("Meal logged successfully");
    }

    @GetMapping("/all")
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }
}