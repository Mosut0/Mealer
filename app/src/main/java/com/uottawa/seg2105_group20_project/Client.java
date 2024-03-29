package com.uottawa.seg2105_group20_project;


public class Client extends Account{
    public String creditCardNumber, CVV, expiry;

    public Client(){} //Needed for compilation
    public Client(String id, String firstName, String lastName, String email, String password, String address, String creditCardNumber, String CVV, String expiry) {

        if ( firstName == null || lastName == null || email == null || password == null || address == null || creditCardNumber == null)
            throw new IllegalArgumentException( "One or more fields are empty!" );

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.CVV = CVV;
        this.expiry = expiry;
    }
}

