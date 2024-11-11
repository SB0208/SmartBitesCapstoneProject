package com.library.backend.service;

import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    public MealServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMeals() {
        Meal meal1 = new Meal("Light Green Salad", "Recipes", "Eating guides", "Type", "http://test.com", "100 kcal");
        Meal meal2 = new Meal("Fresh Fruit Smoothie", "Recipes", "Eating guides", "Type", "http://test.com", "200 kcal");

        when(mealRepository.findAll()).thenReturn(List.of(meal1, meal2));

        List<Meal> meals = mealService.getAllMeals();

        assertEquals(2, meals.size());
    }

    @Test
    void getMealById() throws Exception {
        Meal meal = new Meal("Salad", "Recipes", "Eating guides", "Type", "http://test.com", "100 kcal");
        when(mealRepository.findById("1")).thenReturn(Optional.of(meal));

        Meal foundMeal = mealService.getMealById("1");

        assertNotNull(foundMeal);
        assertEquals("Salad", foundMeal.getName());
    }

    @Test
    void testCreateMeal() {
        Meal meal = new Meal("Salad", "Recipes", "Category", "Type", "http://test.com", "100 kcal");

        when(mealRepository.save(meal)).thenReturn(meal);

        Meal createdMeal = mealService.createMeal(meal);

        assertNotNull(createdMeal);
        assertEquals("Salad", createdMeal.getName());
    }

    @Test
    void testDeleteMeal() {
        Meal meal = new Meal("Meal1", "Description", "Category", "Type", "http://test.com", "100 kcal");

        when(mealRepository.findById("1")).thenReturn(Optional.of(meal));

        mealService.deleteMeal("1");

        verify(mealRepository, times(1)).findById("1");
        verify(mealRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteMealNotFound() {
        when(mealRepository.findById("1")).thenReturn(Optional.empty());

        mealService.deleteMeal("1");

        verify(mealRepository, times(1)).findById("1");
        verify(mealRepository, never()).deleteById("1");
    }

    @Test
    void testGetMealsByCategory() {
        Meal meal1 = new Meal("Salad", "Recipe", "Vegetarian", "Lunch", "http://test.com", "50 kcal");
        Meal meal2 = new Meal("Smoothie", "Recipe", "Vegetarian", "Snack", "http://test.com", "80 kcal");

        when(mealRepository.findByCategory("Vegetarian")).thenReturn(List.of(meal1, meal2));

        List<Meal> meals = mealService.getMealsByCategory("Vegetarian");

        assertEquals(2, meals.size());
        assertEquals("Salad", meals.get(0).getName());
        assertEquals("Smoothie", meals.get(1).getName());
    }

    @Test
    void testGetMealsByType() {
        Meal meal = new Meal("Salad", "Recipe", "Vegan", "Lunch", "http://test.com", "50 kcal");

        when(mealRepository.findByType("Lunch")).thenReturn(List.of(meal));

        List<Meal> meals = mealService.getMealsByType("Lunch");

        assertFalse(meals.isEmpty());
        assertEquals(1, meals.size());
        assertEquals("Salad", meals.get(0).getName());
    }


}