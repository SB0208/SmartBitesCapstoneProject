package com.library.backend.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class MealTest {

    private Meal meal;

    @BeforeEach
    void setUp() {
        List<String> ingredients = Arrays.asList("Tomato", "Cucumber", "Lettuce");
        meal = new Meal(
                "Salad",
                "A healthy green salad",
                "Vegetable",
                "Lunch",
                "http://saladrecipe.com",
                "Low calories",
                "Rich in vitamins"
        );
    }

    @Test
    void testMealInitialization() {
        assertEquals("Salad", meal.getName());
        assertEquals("A healthy green salad", meal.getDescription());
        assertEquals("Vegetable", meal.getCategory());
        assertEquals("Lunch", meal.getType());
        assertEquals("http://saladrecipe.com", meal.getLink());
        assertEquals("Low calories", meal.getNutrition());
        assertEquals("Rich in vitamins", meal.getHealthBenefit());
    }

    @Test
    void testSettersAndGetters() {
        meal.setName("Fruit Salad");
        assertEquals("Fruit Salad", meal.getName());

        meal.setDescription("A tasty fruit mix");
        assertEquals("A tasty fruit mix", meal.getDescription());

        meal.setCategory("Fruit");
        assertEquals("Fruit", meal.getCategory());

        meal.setType("Snack");
        assertEquals("Snack", meal.getType());

        meal.setLink("http://fruitsalad.com");
        assertEquals("http://fruitsalad.com", meal.getLink());


        meal.setNutrition("High in fiber");
        assertEquals("High in fiber", meal.getNutrition());

        meal.setHealthBenefit("Boosts immunity");
        assertEquals("Boosts immunity", meal.getHealthBenefit());
    }


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

    @Test
    void testToString() {
        String expected = "Meal(id=null, name=Salad, description=A healthy green salad, category=Vegetable, type=Lunch, link=http://saladrecipe.com, nutrition=Low calories, healthBenefit=Rich in vitamins)";
        assertEquals(expected, meal.toString());
    }


}