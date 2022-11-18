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

    DatabaseReference dbMenu;
    String cookID;
    RecyclerView recyclerViewMenu;

    ArrayList<Meal> menuList;

    Button mealMenuBackBtn;
    Button addMealBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_menu);

        Bundle bundle = getIntent().getExtras();
        cookID = bundle.getString("cookID");

        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        menuList = new ArrayList<>();
        dbMenu = FirebaseDatabase.getInstance().getReference("meals").child(cookID);
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

        dbMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Meal meal = postSnapshot.getValue(Meal.class);
                    assert meal != null;
                    menuList.add(meal);
                }
                setAdapter();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }

    public void setAdapter(){
        RecyclerAdapterMenu adapter = new RecyclerAdapterMenu(menuList, getIntent().getExtras().getString("cookID"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMenu.setLayoutManager(layoutManager);
        recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenu.setAdapter(adapter);
    }

    public void menuBackClick(){
        Intent i = new Intent(this, welcomeCook.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void addMenu(){
        Intent i = new Intent(this, AddMeal.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }
}