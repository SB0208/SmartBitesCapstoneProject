package com.library.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection ="meals")

public class Meal {
    @Id
    private String id;
    private String userId;
    private String name;
    private int calories;
    private LocalDateTime dateTime;


    public Meal(String id, String userId, String name, int calories, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.calories = calories;
        this.dateTime = dateTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
