package com.library.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MealTest {

    private Meal meal;




    @Test
    void testNotEquals() {
        Meal differentMeal = new Meal(
                "Soup",
                "A warm vegetable soup",
                "Vegetable",
                "Dinner",
                "http://soup.com",
                "Low fat",
                "Good for digestion"
        );

        assertNotEquals(meal, differentMeal);
    }




}