package com.library.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection ="meals")

public class Meal {
    @Id
    private String userId;
    private String name;
    private int calories;
    private LocalDateTime dateTime;


    public Meal(String userId, String name, int calories, LocalDateTime dateTime) {

        this.userId = userId;
        this.name = name;
        this.calories = calories;
        this.dateTime = dateTime;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Meal(){
       this.name = "";
       this.calories = 0;
       this.dateTime = LocalDateTime.now();

    }

    public Meal(String name){
        this.name=name;
    }

    public static Meal getMeal(String mealName,int mealCalories,LocalDateTime dateTime){
       return new Meal(mealName);

    }

    public String getMealName(){
        return name;
    }
    public int getMealCalories(){
        return calories;
    }
    public void setMealName(String mealName){
        this.name=mealName;

    }
    public void setMealCalories(int mealCalories){
        this.calories=mealCalories;
    }
    public void setMealCalories(Meal meal){
        this.calories=meal.getCalories();
    }




}
