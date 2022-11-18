package com.uottawa.seg2105_group20_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class MealMenu extends AppCompatActivity {

    DatabaseReference dbCooks;
    List<Meal> menuList;
    RecyclerView recyclerViewMenu;

    Button mealMenuBackBtn;
    Button addMealBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);
        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        menuList = new ArrayList<>();
        dbCooks = FirebaseDatabase.getInstance().getReference("complaints");
        mealMenuBackBtn = (Button) findViewById(R.id.menuBackBtn);
        addMealBtn = (Button) findViewById(R.id.addMenuItemBtn);

        mealMenuBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                menuBackClick();
            }
        });

        addMealBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addMenu();
            }
        });



    }

    protected void onStart() {
        super.onStart();

        dbCooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Cook cook = postSnapshot.getValue(Cook.class);
                    if(cook != null) { //Don't remember why I added a reviewed attribute
                        cook.setDbId(postSnapshot.getKey());
                        menuList.add(complaint);
                    }
                }
                setAdapter();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }

    public void setAdapter(){
        System.out.println(menuList.size());
        RecyclerAdapterMenu adapter = new RecyclerAdapterMenu(menuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMenu.setLayoutManager(layoutManager);
        recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenu.setAdapter(adapter);
    }

    public void menuBackClick(){
        Intent i = new Intent(this, welcomeCook.class);
        startActivity(i);
    }

    public void addMenu(){

    }
}