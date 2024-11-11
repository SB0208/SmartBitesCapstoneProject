package com.library.backend.dto;

public class MealDTO {
    private String name;
    private String description;
    private String category;
    private String type;
    private String link;
    private String nutrition;


    public MealDTO(String name, String description, String category, String type, String link, String nutrition) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.link = link;
        this.nutrition = nutrition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

