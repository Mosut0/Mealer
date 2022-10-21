package com.uottawa.seg2105_group20_project;

public class Client extends Account{
    final private CreditCard creditCard;

    public Client(String id, String firstName, String lastName, String email, String password, String address, CreditCard creditCard){
        if(firstName == null || lastName == null || email == null || password == null || address == null || creditCard == null){
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
        this.creditCard = creditCard;
    }
    public CreditCard getCreditCard(){return this.creditCard;}
}
