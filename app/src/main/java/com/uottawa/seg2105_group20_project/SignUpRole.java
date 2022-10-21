package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


//Class with sign up role page
public class SignUpRole extends AppCompatActivity {

    protected Button clientBtn, cookBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_role);

        clientBtn = findViewById(R.id.clientButton);
        cookBtn = findViewById(R.id.cookButton);
        backBtn = findViewById(R.id.roleBackButton);

        clientBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clientClick();
            }
        });

        cookBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cookClick();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                backClick();
            }
        });

    }

    public void clientClick () {
        startActivity(new Intent(this, SignUpClient.class));
    }
    public void cookClick () {
        startActivity(new Intent(this, SignUpCook.class));
    }
    public void backClick (){
        startActivity(new Intent(this, MainActivity.class));
    }


}