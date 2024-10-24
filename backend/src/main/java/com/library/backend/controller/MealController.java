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

    private final MealService mealService;
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @GetMapping("/{id}")
     public ResponseEntity<Meal> getMealById(@PathVariable String id) {
        Meal meal = mealService.getMealById(id);
        return meal != null ? ResponseEntity.ok(meal) : ResponseEntity.notFound().build();

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Meal>> getMealsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(mealService.getMealsByCategory(category));
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        Meal createdMeal = mealService.createMeal(meal);
        return ResponseEntity.ok(createdMeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable String id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }




}