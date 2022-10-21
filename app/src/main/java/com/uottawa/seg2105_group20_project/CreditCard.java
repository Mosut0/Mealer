package com.uottawa.seg2105_group20_project;

public class CreditCard {
    final private String creditCardNumber, expirationDate, cvv;

    public CreditCard(String creditCardNumber, String expirationDate, String cvv){
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCreditCardNumber(){return this.creditCardNumber;}
    public String getExpirationDate(){return this.expirationDate;}
    public String getCvv(){return this.cvv;}

}
