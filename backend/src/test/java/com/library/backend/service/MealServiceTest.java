package com.library.backend.service;

import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

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
        Meal meal1 =new Meal("Salad","Light Green Salad","Recepies","Eating guides","https://example.com",null,"100 kcal","Good");
       Meal meal2 = new Meal("Smoothie","Fresh Fruit Smoothie","Recepies","Eating guides","https://example.com",null,"200 kcal","Good");

       when(mealRepository.findAll()).thenReturn(List.of(meal1,meal2));
       List<Meal> meals = mealService.getAllMeals();
       assertEquals(2, meals.size());

    }

    @Test
    void getMealById() throws Exception {
        Meal meal = new Meal("Salad","Light Green Salad","Recepies","Eating guides","https://example.com",null,"100 kcal","Good");
        when(mealRepository.findById("1")).thenReturn(Optional.of(meal));



        Meal foundMeal = mealService.getMealById("1");
        assertNotNull(foundMeal);
        assertEquals("Salad", foundMeal.getName());
    }

   @Test
    void testCreateMeal() {
        Meal meal = new Meal("Salad","Light Green Salad","Recepies","Eating guides","https://example.com",null,"100 kcal","Good");
        when(mealRepository.save(meal)).thenReturn(meal);
        Meal createdMeal = mealService.createMeal(meal);
        assertNotNull(createdMeal);
        assertEquals("Salad", createdMeal.getName());
   }

    @Test
    void testDeleteMeal() {
        mealService.deleteMeal("1");
        verify(mealRepository, times(1)).deleteById("1");
    }

}