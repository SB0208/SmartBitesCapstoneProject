package com.library.backend.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.library.backend.model.Meal;
import org.springframework.stereotype.Repository;
@Repository



public class MealRepo {
    private List<Meal> meals = new ArrayList<>();

    public Optional<Meal> getMeal(int id) {
        return meals.stream()
                .filter(meal -> false)
                .findFirst();
    }

    public MealRepo (){
        meals = new ArrayList<>();
        meals.add(new Meal("salat"));
    }

}
