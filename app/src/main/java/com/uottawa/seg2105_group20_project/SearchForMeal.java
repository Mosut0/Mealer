package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;
import android.widget.Toast;

public class SearchForMeal extends AppCompatActivity {
    DatabaseReference databaseMenu;
    DatabaseReference purchaseRequests;
    DatabaseReference databaseCook;

    RecyclerView recyclerViewSearchMeal;

    List<Cook> cooks;
    List<Meal> meals;

    EditText editMealName;
    EditText editMealType;
    EditText editCuisineType;

    Button searchForMealBtn;
    Button searchForMealBackBtn;

    String MealName = "placeHolderText";
    String MealType = "placeHolderText";
    String CuisineType = "placeHolderText";
    String clientID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_meal);
        Bundle bundle = getIntent().getExtras();

        clientID = bundle.getString("clientID");

        editMealName = (EditText) findViewById(R.id.mealNameSearch);
        editMealType = (EditText) findViewById(R.id.mealTypeSearch);
        editCuisineType = (EditText) findViewById(R.id.cuisineTypeSearch);

        searchForMealBtn = (Button) findViewById(R.id.searchBtn);
        searchForMealBackBtn = (Button) findViewById(R.id.searchForMealBackBtn);

        purchaseRequests = FirebaseDatabase.getInstance().getReference("purchaseRequests");
        databaseMenu = FirebaseDatabase.getInstance().getReference("meals");
        databaseCook = FirebaseDatabase.getInstance().getReference("cooks");

        recyclerViewSearchMeal = findViewById(R.id.recyclerViewSearchForMeal);

        meals = new ArrayList<>();
        cooks = new ArrayList<>();

        searchForMealBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchForMealBtnClick();
            }
        });

        searchForMealBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchForMealBackBtnClick();
            }
        });

    }

    protected void onStart() {
        super.onStart();

        databaseMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                meals.clear();

                for (DataSnapshot dbCookMeal : dataSnapshot.getChildren()) {
                    for (DataSnapshot dbMeal: dbCookMeal.getChildren()){
                        Meal meal = dbMeal.getValue(Meal.class);
                        assert meal != null;
                        if(cookIsActive(meal.getCookID()) && meal.isOffered()){
                            if(meal.getMealName().contains(MealName) || meal.getMealType().contains(MealType) || meal.getCuisineType().contains(CuisineType)){
                                meals.add(meal);
                            }
                        }
                    }
                }
                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        databaseCook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cooks.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Cook cook = postSnapshot.getValue(Cook.class);
                    cooks.add(cook);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    public void setAdapter(){
        System.out.println(meals.size());
        System.out.println(cooks.size());

        RecyclerAdapterSearchMeal adapter = new RecyclerAdapterSearchMeal(meals, cooks, clientID);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSearchMeal.setLayoutManager(layoutManager);
        recyclerViewSearchMeal.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearchMeal.setAdapter(adapter);
    }

    public boolean cookIsActive(String cookID){
        for (int i=0; i<cooks.size(); i++){
            if (cooks.get(i).id.equals(cookID) && cooks.get(i).suspension.equals("Active")){
                return true;
            }
        }
        return false;
    }


    public void searchForMealBtnClick(){
        MealName = editMealName.getText().toString().trim();
        MealType = editMealType.getText().toString().trim();
        CuisineType = editCuisineType.getText().toString().trim();
        if (CuisineType.equals("") && MealName.equals("") && MealType.equals("")){
            Toast.makeText(this, "Please Fill at least 1 Field", Toast.LENGTH_LONG).show();
        }
        if (MealName.equals("")){
            MealName = "placeHolderText";
        }
        if (MealType.equals("")){
            MealType = "placeHolderText";
        }
        if (CuisineType.equals("")){
            CuisineType = "placeHolderText";
        }
        onStart();
    }

    public void searchForMealBackBtnClick(){
        Intent i = new Intent(this, welcomeClient.class);
        Bundle bundle = new Bundle();
        bundle.putString("clientID", clientID);
        i.putExtras(bundle);
        startActivity(i);
    }
}