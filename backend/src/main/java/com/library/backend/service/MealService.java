package com.library.backend.service;
import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public void logMeal(Meal meal) {
        mealRepository.save(meal);
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
