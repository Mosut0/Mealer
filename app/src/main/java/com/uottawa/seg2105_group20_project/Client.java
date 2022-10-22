package com.uottawa.seg2105_group20_project;

public class Client extends Account{
    public CreditCard creditCard;

    public Client(String id, String firstName, String lastName, String email, String password, String address, CreditCard creditCard){
        if(firstName == null || lastName == null || email == null || password == null || address == null || creditCard == null){
            throw new IllegalArgumentException("One or more fields are empty!");
        }
        
        this.id = id;
        this.firstName =firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.creditCard = creditCard;
    }
}
