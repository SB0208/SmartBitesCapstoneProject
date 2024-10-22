package com.library.backend.repository;
import com.library.backend.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface MealRepository extends MongoRepository<Meal, String> {

}
