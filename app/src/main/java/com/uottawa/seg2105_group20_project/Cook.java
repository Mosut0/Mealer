package com.uottawa.seg2105_group20_project;

public class Cook extends Account{
    final private String description, voidCheque;

    public Cook(String id, String firstName, String lastName, String email, String password, String address, String description, String voidCheque){
        if(firstName == null || lastName == null || email == null || password == null || address == null || description == null || voidCheque == null){
            throw new IllegalArgumentException("One or more fields are empty!");
        }else if(!email.contains("@")){
            throw new IllegalArgumentException("Invalid email!");
        }
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setAddress(address);
        this.description = description;
        this.voidCheque = voidCheque;
    }

    public String getDescription(){return this.description;}
    public String getVoidCheque(){return this.voidCheque;}
}
