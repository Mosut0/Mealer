package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//Class for main login page
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.adminButton:
                adminClick(view);
                break;
            case R.id.signupButton:
                signUpClick(view);
                break;
            case R.id.loginButton:
                loginClick(view);
                break;
        }
    }

    public void adminClick (View v){
        Intent i = new Intent(this, LoginAdmin.class);
        startActivity(i);
    }

    public void signUpClick (View v) {
        Intent i = new Intent(this, SignupRole.class);
        startActivity(i);
    }

    public void loginClick (View v){
        Intent i = new Intent(this, WelcomePage.class);
        startActivity(i);
    }

}