package com.library.backend.AI;
import org.springframework.stereotype.Service;
@Service

public class NutritionAIService {

    public String generateMealRecommendation(String calorieGoal) {
        if (calorieGoal < 1500) {
            return "Recommended Meal: Salad,Chicken Breast,Quinoa " + calorieGoal;
        } else if (calorieGoal < 2000) {
            return "Recommended Meal: Salmon,Vegetables,Brown Rice " + calorieGoal;

        } else {
            return "Recommended Meal: Steak,Sweet potatoes,Brokkoli " + calorieGoal;
        }
    }
    }

