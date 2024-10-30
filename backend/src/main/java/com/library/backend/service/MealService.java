package com.library.backend.service;
import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class MealService {

    private final MealRepository mealRepository;
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals() {

        return mealRepository.findAll();
    }


    public Meal getMealById(String id) {
        return mealRepository.findById(id).orElse(null);
    }

    public List<Meal> getMealsByCategory(String category) {
        return mealRepository.findByCategory(category);
    }

    public List<Meal> getMealsByType(String type) {
        return mealRepository.findByType(type);
    }

    public List<Meal> getMealsByCategoryAndType(String category, String type) {
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
            meal.setType(updatedMeal.getType());
            meal.setIngredients(updatedMeal.getIngredients());
            meal.setNutrition(updatedMeal.getNutrition());
            meal.setHealthBenefit(updatedMeal.getHealthBenefit());
            return mealRepository.save(meal);
        }).orElse(null);
    }

    public void deleteMeal(String id) {
        mealRepository.deleteById(id);
    }







}
