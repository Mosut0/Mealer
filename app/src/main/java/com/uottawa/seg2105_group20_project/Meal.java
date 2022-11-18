package com.uottawa.seg2105_group20_project;

import java.util.ArrayList;

public class Meal {
    private String mealID, mealName, mealType, cuisineType, price, description, ingredients, allergens, cookID;
    private boolean offered;
    public Meal(){}

    public Meal(String mealID, String mealName, String mealType, String cuisineType, String ingredients, String allergens, String price, String description,   String cookID, boolean offered) {
        this.mealID = mealID;
        this.mealName = mealName;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.price = price;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.cookID = cookID;
        this.offered = false;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getCookID() {
        return cookID;
    }

    public void setCookID(String cookID) {
        this.cookID = cookID;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }
}
