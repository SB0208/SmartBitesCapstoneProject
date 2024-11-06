package com.library.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="meals")

public class Meal {
    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private String type;
    private String link;
    private List<String> ingredients;
    private String nutrition;
    private String healthBenefit;

    
   public Meal(String name, String description, String category, String type, String link, List<String> ingredients, String nutrition, String healthBenefit) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.link = link;
       this.ingredients = ingredients;
       this.nutrition = nutrition;
       this.healthBenefit = healthBenefit;
   }
   public String getId() {
        return id;
   }

   public void setId(String id) {
        this.id = id;
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

   public String getLink() {
        return link;
   }

   public void setLink(String link) {
        this.link = link;
   }


    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getHealthBenefit() {
        return healthBenefit;
    }

    public void setHealthBenefit(String healthBenefit) {
        this.healthBenefit = healthBenefit;
    }






}
