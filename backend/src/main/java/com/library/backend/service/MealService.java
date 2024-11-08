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
        meal.setIngredients(meal.getIngredients());


        return mealRepository.findById(id).orElse(null);
    }

     public List<Meal> getMealsByCategory(String category)  {
        for (Meal meal : getAllMeals()) {
            if (meal.getCategory().equals(category)) {
                meal.setIngredients(meal.getIngredients());

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
        Meal meal = mealRepository.findByCategory(category).get(1);
        boolean category1 = meal.getCategory().equals("category");

            meal.setIngredients(meal.getIngredients());
            meal.setType(type);
            return mealRepository.findByCategory(category);




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
        if (mealRepository.findById(id).isPresent()) {
            mealRepository.deleteById(id);

        }


    }







}
