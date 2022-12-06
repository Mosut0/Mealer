package com.uottawa.seg2105_group20_project;

import java.util.ArrayList;

public class Cook extends Account {
    public int sales;
    public double rating;
    public String description;
    public String voidCheque;
    public String suspension;
    private ArrayList<Meal> meals;
    private ArrayList<Meal> offeredMeals;

    public Cook(){} //Needed for compilation
    public Cook(String id, String firstName, String lastName, String email, String password, String address, String description, String voidCheque, ArrayList<Meal> meals, ArrayList<Meal> offeredMeals) {

        if ( firstName == null || lastName == null || email == null || password == null || address == null || description == null || voidCheque == null )
            throw new IllegalArgumentException( "One or more fields are empty!" );

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.description = description;
        this.voidCheque = voidCheque;
        this.id = id;
        this.suspension = "Active";
        this.meals = meals;
        this.offeredMeals = offeredMeals;
        this.sales = 0;
        this.rating = 0;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Meal> getOfferedMeals() {
        return offeredMeals;
    }

    public void setOfferedMeals(ArrayList<Meal> offeredMeals) {
        this.offeredMeals = offeredMeals;
    }
}
