package com.uottawa.seg2105_group20_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class welcomeCook extends Activity{


    Button logOutCookBtn, viewMenuBtn, viewOfferedMenuBtn;
    TextView cookStatusText;

    DatabaseReference dbCooks;
    String cookID;
    String suspension;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomecook);

        Bundle bundle = getIntent().getExtras();
        cookID = bundle.getString("cookID");
        dbCooks = FirebaseDatabase.getInstance().getReference("cooks");

        logOutCookBtn = (Button) findViewById(R.id.logOutButtonCook);
        viewMenuBtn = (Button) findViewById(R.id.mealListButton);
        viewOfferedMenuBtn = (Button) findViewById(R.id.offeredMealListButton);
        cookStatusText = (TextView) findViewById(R.id.cookStatusText);

        ((TextView) findViewById(R.id.cookStatusText)).setText("Status: " + suspension);

        logOutCookBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cookLogOffClick();
            }
        });

        viewMenuBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewMenuClick();
            }
        });

        viewOfferedMenuBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewOfferedMenuClick();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbCooks.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    Cook cook = postSnapShot.getValue(Cook.class);
                    assert cook != null;
                    if(cook.id.equals(cookID)){
                        cookStatusText.setText("Status:\n" + cook.suspension);
                        //ADD HERE
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void cookLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void viewMenuClick(){
        Intent i = new Intent(this, MealMenu.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void viewOfferedMenuClick(){
        Intent i = new Intent(this, OfferedMealMenu.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }
}
