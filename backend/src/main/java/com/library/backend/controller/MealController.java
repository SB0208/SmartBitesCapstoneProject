package com.library.backend.controller;
import com.library.backend.model.Meal;
import com.library.backend.service.MealService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")

public final class MealController {

    private final MealService mealService;
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @GetMapping("/{id}")
     public ResponseEntity<Meal> getMealById(@PathVariable String id) throws Exception {
        Meal meal = mealService.getMealById(id);
        return meal != null ? ResponseEntity.ok(meal) : ResponseEntity.notFound().build();

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Meal>> getMealsByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok(mealService.getMealsByCategory(category));
        } catch (Exception e) {
            throw new  RuntimeException("Internal Server Error", e);
        }
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Hier kannst du die Nachricht und den Statuscode anpassen
        return ResponseEntity.status(500).body(ex.getMessage());
    }


    @GetMapping("/category/{category}/type/{type}")
    public ResponseEntity<List<Meal>> getMealsByCategoryAndType(@PathVariable String category, @PathVariable String type) {
        return ResponseEntity.ok(mealService.getMealsByCategoryAndType(category, type));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Meal>> getMealsByCategory() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @PostMapping("/categories/category")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        Meal createdMeal = mealService.createMeal(meal);
        return ResponseEntity.ok(createdMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable String id, @RequestBody Meal updatedMeal) {
        Meal meal = mealService.updateMeal(id, updatedMeal);
        return meal != null ? ResponseEntity.ok(meal) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable String id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }




}