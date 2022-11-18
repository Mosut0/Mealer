package com.uottawa.seg2105_group20_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

public class AddMeal extends AppCompatActivity {

    String cookID;

    DatabaseReference dbMenu;

    Button addMealBtn, addMealBackBtn;

    EditText mealNameText;
    EditText mealTypeText;
    EditText cuisineTypeText;
    EditText ingredientsText;
    EditText allergensText;
    EditText priceText;
    EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        Bundle bundle = getIntent().getExtras();
        cookID = bundle.getString("cookID");
        dbMenu = FirebaseDatabase.getInstance().getReference("meals").child(cookID);

        mealNameText = (EditText) findViewById(R.id.mealNameInput);
        mealTypeText = (EditText) findViewById(R.id.mealTypeInput);
        cuisineTypeText = (EditText) findViewById(R.id.cuisineTypeInput);
        ingredientsText = (EditText) findViewById(R.id.ingredientsInput);
        allergensText = (EditText) findViewById(R.id.allergensInput);
        priceText = (EditText) findViewById(R.id.mealPriceInput);
        descriptionText = (EditText) findViewById(R.id.mealDescriptionInput);

        addMealBackBtn = (Button) findViewById(R.id.menuBackBtn);
        addMealBtn = (Button) findViewById(R.id.addToMenuBtn);

        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMenuClick();
            }
        });

        addMealBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMealBackClick();
            }
        });
    }

    public void addMenuClick(){
        String mealName = mealNameText.getText().toString().trim();
        String mealType = mealTypeText.getText().toString().trim();
        String cuisineType = cuisineTypeText.getText().toString().trim();
        String ingredients = ingredientsText.getText().toString().trim();
        String allergens = allergensText.getText().toString().trim();
        String price = priceText.getText().toString().trim();
        String description = descriptionText.getText().toString().trim();

        if(!TextUtils.isEmpty(mealName) && !TextUtils.isEmpty(mealType) && !TextUtils.isEmpty(cuisineType) && !TextUtils.isEmpty(ingredients) && !TextUtils.isEmpty(allergens) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(description)){
            String id = dbMenu.push().getKey();

            Meal meal = new Meal(id, mealName, mealType, cuisineType, ingredients, allergens, price, description, cookID, false);

            dbMenu.child(id).setValue(meal);

            mealNameText.setText("");
            mealTypeText.setText("");
            cuisineTypeText.setText("");
            ingredientsText.setText("");
            allergensText.setText("");
            priceText.setText("");
            descriptionText.setText("");

            Toast.makeText(this, "Successfully Added Meal!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MealMenu.class);
            i.putExtra("cookID", cookID);
            startActivity(i);
        } else {
            Toast.makeText(this, "One or Many Fields Are Empty", Toast.LENGTH_LONG).show();
        }
    }

    public void addMealBackClick(){
        Intent i = new Intent(this, MealMenu.class);
        i.putExtra("cookID", getIntent().getExtras().getString("cookID"));
        startActivity(i);
    }
}