package com.uottawa.seg2105_group20_project;

public class Admin extends Account {

    public Admin(){} //Needed for compilation
    public Admin(String userName, String password){
        this.email = userName;
        this.password = password;
    }
}