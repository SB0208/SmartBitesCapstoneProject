package com.library.backend.service;
import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MealService {

    private MealRepository mealRepository;
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Optional<Meal> getMealById(String mealId) {
        return mealRepository.findById(mealId);

    }


    public void addMeal(Meal meal) {
        mealRepository.save(meal);
    }

    public void saveMeal(Meal meal) {
        mealRepository.save(meal);
    }





}
