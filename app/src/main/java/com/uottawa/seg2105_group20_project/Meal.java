package com.uottawa.seg2105_group20_project;

import java.util.ArrayList;

public class Meal {
    private String mealName, mealType, cuisineType, price, description, dbID;
    private ArrayList<String> ingredients, allergens;
    public Meal(){}

    public Meal(String mealName, String mealType, String cuisineType, String price, String description, ArrayList<String> ingredients, ArrayList<String> allergens, String dbID) {
        this.mealName = mealName;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.price = price;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.dbID = dbID;
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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
        this.allergens = allergens;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }
}
