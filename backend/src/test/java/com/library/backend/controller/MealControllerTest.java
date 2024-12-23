package com.library.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.backend.model.Meal;
import com.library.backend.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MealController.class)
class MealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @Autowired
    private ObjectMapper objectMapper;

    private Meal meal;
    private List<Meal> mealList;

    @BeforeEach
    void setUp() {
        meal = new Meal("Salad","Healthy salad","Vegetable","Lunch","http://saladrecipe.com","220 kcal");
        mealList = Arrays.asList(meal);
    }

    @Test
    void getAllMeals_shouldReturnMealList() throws Exception {
        when(mealService.getAllMeals()).thenReturn(mealList);

        mockMvc.perform(get("/api/meals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(meal.getName())));

        verify(mealService, times(1)).getAllMeals();
    }

    @Test
    void getMealById_shouldReturnMeal() throws Exception {
        when(mealService.getMealById("1")).thenReturn(meal);

        mockMvc.perform(get("/api/meals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(meal.getName())));

        verify(mealService, times(1)).getMealById("1");
    }

    @Test
    void getMealById_shouldReturnNotFound() throws Exception {
        when(mealService.getMealById("2")).thenReturn(null);

        mockMvc.perform(get("/api/meals/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(mealService, times(1)).getMealById("2");
    }

    @Test
    void getMealsByCategory_shouldReturnMealList() throws Exception {
        when(mealService.getMealsByCategory("Vegetable")).thenReturn(mealList);

        mockMvc.perform(get("/api/meals/category/Vegetable")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is(meal.getCategory())));

        verify(mealService, times(1)).getMealsByCategory("Vegetable");
    }

    @Test
    void getMealsByCategory_shouldReturnEmptyList() throws Exception {
        when(mealService.getMealsByCategory("UnknownCategory")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/meals/category/UnknownCategory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(mealService, times(1)).getMealsByCategory("UnknownCategory");
    }

    @Test
    void getMealsByCategory_shouldHandleException() throws Exception {
        // Wenn die Service-Methode eine Exception wirft
        when(mealService.getMealsByCategory("Vegetable")).thenThrow(new RuntimeException("Internal Server Error"));

        // Durchführung der Anfrage und Überprüfung auf Fehlerstatus 500
        mockMvc.perform(get("/api/meals/category/Vegetable")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())  // Überprüfung des 500-Fehlerstatus
                .andExpect(content().string("Internal Server Error"));  // Optional: Überprüfung der Fehlermeldung im Response Body

        verify(mealService, times(1)).getMealsByCategory("Vegetable");
    }



    @Test
    void createMeal_shouldReturnCreatedMeal() throws Exception {
        when(mealService.createMeal(any(Meal.class))).thenReturn(meal);

        mockMvc.perform(post("/api/meals/categories/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(meal.getName())));

        verify(mealService, times(1)).createMeal(any(Meal.class));
    }

    @Test
    void updateMeal_shouldReturnUpdatedMeal() throws Exception {
        when(mealService.updateMeal(eq("1"), any(Meal.class))).thenReturn(meal);

        mockMvc.perform(put("/api/meals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(meal.getName())));

        verify(mealService, times(1)).updateMeal(eq("1"), any(Meal.class));
    }


    @Test
    void getMealsByCategoryAndType_shouldReturnFilteredMealList() throws Exception {
        // Mocking MealService to return the filtered list
        when(mealService.getMealsByCategoryAndType("Vegetable", "Lunch")).thenReturn(mealList);

        mockMvc.perform(get("/api/meals/category/Vegetable/type/Lunch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is(meal.getCategory())))
                .andExpect(jsonPath("$[0].type", is(meal.getType())));

        verify(mealService, times(1)).getMealsByCategoryAndType("Vegetable", "Lunch");
    }


    @Test
    void deleteMeal_shouldReturnNoContent() throws Exception {
        doNothing().when(mealService).deleteMeal("1");

        mockMvc.perform(delete("/api/meals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(mealService, times(1)).deleteMeal("1");
    }
}