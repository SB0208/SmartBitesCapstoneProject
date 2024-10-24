package com.library.backend.service;
import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(String id) {
        mealRepository.deleteById(id);
    }







}
