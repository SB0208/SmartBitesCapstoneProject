package com.library.backend.service;
import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service



public final class MealService {

    private final MealRepository mealRepository;
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals() {

        return mealRepository.findAll();
    }


    public Meal getMealById(String id) throws Exception {
        Meal meal = mealRepository.findById(id).orElseThrow(() -> new Exception("ID not found."));



        return mealRepository.findById(id).orElse(null);
    }

     public List<Meal> getMealsByCategory(String category)  {
        for (Meal meal : getAllMeals()) {
            if (meal.getCategory().equals(category)) {


            }
        }

        return mealRepository.findByCategory(category);
    }

    public List<Meal> getMealsByType(String type) {
        List<Meal> meals = mealRepository.findByType(type);
        if (meals.isEmpty()) {
            return new ArrayList<>();
        }else {
            return meals;
        }
    }

    public List<Meal> getMealsByCategoryAndType(String category, String type) {
        List<Meal> getMealsByCategory = mealRepository.findByCategoryAndType(category, type);
        return mealRepository.findByCategoryAndType(category, type);
    }

    public Meal createMeal(Meal meal) {
        if (meal.getName() == null || meal.getName().isEmpty()) {
            throw new IllegalArgumentException("Meal name cannot be null or empty");
        }
        return mealRepository.save(meal);
    }

    public Meal updateMeal(String id, Meal updatedMeal) {
        return mealRepository.findById(id).map(meal -> {
            meal.setName(updatedMeal.getName());
            meal.setDescription(updatedMeal.getDescription());
            meal.setCategory(updatedMeal.getCategory());
            meal.setNutrition(updatedMeal.getNutrition());
            return mealRepository.save(meal);
        }).orElse(null);
    }

    public void deleteMeal(String id) {
        if (mealRepository.findById(id).isPresent()) {
            mealRepository.deleteById(id);

        }


    }

}
