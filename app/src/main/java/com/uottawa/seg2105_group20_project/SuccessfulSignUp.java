package com.uottawa.seg2105_group20_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Class for welcome page
public class SuccessfulSignUp extends AppCompatActivity {

    Button backToLoginPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_signup);

        backToLoginPageBtn = findViewById(R.id.loginAfterSignUpButton);

        backToLoginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogin(view);
            }
        });
    }

    public void backToLogin(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}