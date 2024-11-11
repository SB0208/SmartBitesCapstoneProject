package com.library.backend;

import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;
import com.library.backend.service.MealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BackendApplicationTests {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MealService mealService;

    @Test
    public void contextLoads() {
        // This test ensures the Spring application context loads successfully
    }

    @Test
    public void testMealRepositoryLoads() {
        // This test verifies that MealRepository is loaded into the application context
        assertNotNull(mealRepository, "MealRepository should be loaded into the context.");
    }

    @Test
    public void testMealServiceLoads() {
        // This test verifies that MealService is loaded into the application context
        assertNotNull(mealService, "MealService should be loaded into the context.");
    }



    @Test
    public void testMealServiceGetAllMeals() {
        // Add a sample meal
        Meal meal = new Meal("2", "Another Meal", "Description", "Category", "Type", "www.test.com", "220kcal", null);
        mealRepository.save(meal);

        // Call the service method to get all meals and check that the list is not empty
        assertTrue(mealService.getAllMeals().size() > 0, "MealService should return a non-empty list of meals.");

        // Clean up
        mealRepository.deleteById("2");
    }
}

