package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;

//Class for admin login page
public class Account extends AppCompatActivity {
    private String id, firstName, lastName, email, password, address;

    public String getId(){return this.id;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getAddress(){return this.address;}

    public void setId(String id){this.id = id;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = this.password;}
    public void setAddress(String address){this.address = this.address;}
}