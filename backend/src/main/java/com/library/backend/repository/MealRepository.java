package com.library.backend.repository;
import com.library.backend.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MealRepository extends MongoRepository<Meal, String> {
    List<Meal> findByCategory(String category);
    List<Meal> findByType(String type);

}
