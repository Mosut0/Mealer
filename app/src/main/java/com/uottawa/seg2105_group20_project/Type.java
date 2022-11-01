package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Type extends Activity {

    Button clientBtn;
    Button cookBtn;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        clientBtn = (Button) findViewById(R.id.clientButton);
        cookBtn = (Button) findViewById(R.id.cookButton);
        backBtn = (Button) findViewById(R.id.roleBackButton);

        clientBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clientTypeClick();
            }
        });

        cookBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cookTypeClick();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                backBtnOnClick();
            }
        });
    }

    protected void clientTypeClick (){
        Intent i = new Intent(this, ClientSignUp.class);
        startActivity(i);
    }

    protected void cookTypeClick () {
        Intent i = new Intent(this, CookSignUp.class);
        startActivity(i);
    }

    protected void backBtnOnClick(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}