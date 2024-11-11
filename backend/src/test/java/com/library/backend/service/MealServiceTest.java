package com.library.backend.service;

import com.library.backend.model.Meal;
import com.library.backend.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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
    void getMealById_schouldReturnMeal() throws Exception {
        Meal meal = new Meal("Salad", "Recipes", "Eating guides", "Type", "http://test.com", "100 kcal");
        when(mealRepository.findById("1")).thenReturn(Optional.of(meal));

        Meal foundMeal = mealService.getMealById("1");

        assertNotNull(foundMeal);
        assertEquals("Salad", foundMeal.getName());
    }

    @Test
    void getMealById_shouldThrowExceptionWhenNotFound() {
        when(mealRepository.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> mealService.getMealById("1"));
        assertEquals("ID not found.", exception.getMessage());
    }

    @Test
    void testCreateMeal_shouldReturnSavedMeal() {
        Meal meal = new Meal("Salad", "Recipes", "Category", "Type", "http://test.com", "100 kcal");

        when(mealRepository.save(meal)).thenReturn(meal);

        Meal createdMeal = mealService.createMeal(meal);

        assertNotNull(createdMeal,"Das zurückgegebene Meal sollte nicht null sein.");
        assertEquals("Salad", createdMeal.getName(),"Der Name des Meal sollte 'Salad' sein.");
        verify(mealRepository, times(1)).save(meal);
    }

    @Test
    void createMeal_shouldThrowExceptionWhenNameIsNull() {
        Meal meal = new Meal(null, "Healthy salad", "Vegetable", "Lunch", "http://saladrecipe.com", "220 kcal");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mealService.createMeal(meal));
        assertEquals("Meal name cannot be null or empty", exception.getMessage());
        verify(mealRepository, never()).save(any(Meal.class));
    }

    @Test
    void createMeal_shouldThrowExceptionWhenNameIsEmpty() {
        // Arrange
        Meal meal = new Meal("", "Healthy salad", "Vegetable", "Lunch", "http://saladrecipe.com", "220 kcal");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mealService.createMeal(meal));
        assertEquals("Meal name cannot be null or empty", exception.getMessage());
        verify(mealRepository, never()).save(any(Meal.class));
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

    @Test
    void testGetMealsByCategoryAndType() {
        MealRepository mealRepositoryMock = mock(MealRepository.class);


        MealService mealService = new MealService(mealRepositoryMock);


        Meal meal1 = new Meal("Pizza","Vegetarian","category","type","www.test.com","300 kcal");
        Meal meal2 = new Meal("Pasta","Vegetarian","categori","types","www.test.com","308 kcal");
        List<Meal> mealList = new ArrayList<>();
        mealList.add(meal1);
        mealList.add(meal2);


        when(mealRepositoryMock.findByCategoryAndType("category", "type")).thenReturn(mealList);


        List<Meal> meals = mealService.getMealsByCategoryAndType("category", "type");


        assertNotNull(meals);
        assertEquals(2, meals.size());
        assertEquals("Pizza", meals.get(0).getName());
        assertEquals("Pasta", meals.get(1).getName());


        verify(mealRepositoryMock, times(2)).findByCategoryAndType("category", "type");
    }

    @Test
    void testUpdateMeal() {

        MealRepository mealRepository = mock(MealRepository.class);


        MealService mealService = new MealService(mealRepository);


        Meal existingMeal = new Meal("Old Salad", "Old Description", "Old Category", "Old Type", "http://old-link.com","100 kcal");


        Meal updatedMeal = new Meal("New Salad", "Fresh vegetables", "Vegan", "Lunch", "http://new-link.com","50 kcal");


        when(mealRepository.findById("1")).thenReturn(Optional.of(existingMeal));
        when(mealRepository.save(any(Meal.class))).thenAnswer(invocation -> invocation.getArgument(0));


        Meal result = mealService.updateMeal("1", updatedMeal);


        assertNotNull(result, "Das aktualisierte Meal-Objekt sollte nicht null sein.");
        assertEquals("New Salad", result.getName(), "Der Name sollte aktualisiert sein.");
        assertEquals("Fresh vegetables", result.getDescription(), "Die Beschreibung sollte aktualisiert sein.");
        assertEquals("Vegan", result.getCategory(), "Die Kategorie sollte aktualisiert sein.");
        assertEquals("50 kcal", result.getNutrition(), "Die Nährwertangabe sollte aktualisiert sein.");

        // Sicherstellen, dass die save-Methode im Repository aufgerufen wurde
        verify(mealRepository, times(1)).save(existingMeal);
    }

    @Test
    void testUpdateMealNotFound() {

        MealRepository mealRepository = mock(MealRepository.class);


        MealService mealService = new MealService(mealRepository);


        Meal updatedMeal = new Meal("Spinat","Vegetable","Vegetables","type","www.test.com","200kcal");


        when(mealRepository.findById("1")).thenReturn(Optional.empty());


        Meal result = mealService.updateMeal("1", updatedMeal);


        assertNull(result, "Das Ergebnis sollte null sein, wenn kein Meal mit der gegebenen ID gefunden wird.");


        verify(mealRepository, times(0)).save(any(Meal.class));
    }

}