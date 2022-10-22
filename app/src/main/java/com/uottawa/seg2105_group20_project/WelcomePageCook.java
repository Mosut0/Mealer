package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

//Class for welcome page
public class WelcomePageCook extends AppCompatActivity {

    Button logOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_cook);

        logOffBtn = findViewById(R.id.logOutButton);

        logOffBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logOutClick(view);
            }
        });
    }

    public void logOutClick(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}