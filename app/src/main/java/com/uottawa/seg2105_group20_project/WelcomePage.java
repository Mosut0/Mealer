package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

//Class for welcome page
public class WelcomePage extends AppCompatActivity {

    Button logOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        logOffBtn = findViewById(R.id.logOutButton);

        logOffBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                logOutClick();
            }
        });
    }

    private void logOutClick(){
        startActivity(new Intent(this, MainActivity.class));
    }
}