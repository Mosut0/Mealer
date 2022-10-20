package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Class for main login page
public class MainActivity extends AppCompatActivity {
    private Button adminButton;
    private Button signupButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminButton = (Button) findViewById(R.id.adminButton);
        signupButton = (Button) findViewById(R.id.signupButton);
        loginButton = (Button) findViewById(R.id.loginButton);



        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminClick(view);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpClick(view);
            }
        });
//
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginClick(view);
            }
        });
    }

    public void adminClick (View v){
        startActivity(new Intent(this, LoginAdmin.class));
    }

    public void signUpClick (View v) {
        startActivity(new Intent(this, SignupRole.class));
    }

    public void loginClick (View v){
        startActivity(new Intent(this, WelcomePage.class));
    }

}