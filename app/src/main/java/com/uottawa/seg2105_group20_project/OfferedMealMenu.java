package com.uottawa.seg2105_group20_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OfferedMealMenu extends AppCompatActivity {

    DatabaseReference dbMenu;
    String cookID;
    RecyclerView recyclerViewOMenu;

    ArrayList<Meal> offeredMenuList;

    Button mealOMenuBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_meal_menu);

        Bundle bundle = getIntent().getExtras();
        cookID = bundle.getString("cookID");

        recyclerViewOMenu = findViewById(R.id.recyclerViewOMenu);
        offeredMenuList = new ArrayList<>();
        dbMenu = FirebaseDatabase.getInstance().getReference("meals").child(cookID);
        mealOMenuBackBtn = (Button) findViewById(R.id.oMenuBackBtn);

        mealOMenuBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                menuBackClick();
            }
        });
    }

    protected void onStart() {
        super.onStart();

        dbMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                offeredMenuList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Meal meal = postSnapshot.getValue(Meal.class);
                    assert meal != null;
                    if(meal.isOffered())
                        offeredMenuList.add(meal);
                }
                setAdapter();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }

    public void setAdapter(){
        RecyclerAdapterOfferedMenu adapter = new RecyclerAdapterOfferedMenu(offeredMenuList, getIntent().getExtras().getString("cookID"));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewOMenu.setLayoutManager(layoutManager);
        recyclerViewOMenu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOMenu.setAdapter(adapter);
    }

    public void menuBackClick(){
        Intent i = new Intent(this, welcomeCook.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }
}