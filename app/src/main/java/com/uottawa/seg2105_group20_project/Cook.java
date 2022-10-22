package com.uottawa.seg2105_group20_project;

public class Cook extends Account{
    public String description, voidCheque;

    public Cook(String id, String firstName, String lastName, String email, String password, String address, String description, String voidCheque){
        if(firstName == null || lastName == null || email == null || password == null || address == null || description == null || voidCheque == null){
            throw new IllegalArgumentException("One or more fields are empty!");
        }else if(!email.contains("@")){
            throw new IllegalArgumentException("Invalid email!");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.description = description;
        this.voidCheque = voidCheque;
    }
}
