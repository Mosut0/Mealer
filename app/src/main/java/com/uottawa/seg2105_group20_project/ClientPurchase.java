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

public class ClientPurchase extends AppCompatActivity {
    DatabaseReference dbPurchaseRequests;
    DatabaseReference dbMeals;
    DatabaseReference dbCooks;

    RecyclerView recyclerViewClientPurchase;
    Button purchasesMenuBackBtn;

    List<PurchaseRequest> purchaseList;
    List<Meal> meals;
    List<Cook> cooks;
    String clientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_purchase);
        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getString("clientID");

        purchasesMenuBackBtn = (Button) findViewById(R.id.clientPurchaseBackBtn);
        recyclerViewClientPurchase = findViewById(R.id.recyclerViewClientPurchase);

        purchaseList = new ArrayList<>();
        meals = new ArrayList<>();
        cooks = new ArrayList<>();

        dbPurchaseRequests = FirebaseDatabase.getInstance().getReference("purchaseRequests");
        dbMeals = FirebaseDatabase.getInstance().getReference("meals");
        dbCooks = FirebaseDatabase.getInstance().getReference("cooks");

        purchasesMenuBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                purchasesMenuBackBtnClick();
            }
        });
    }

    protected void onStart() {
        super.onStart();

        dbPurchaseRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                purchaseList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PurchaseRequest purchase = postSnapshot.getValue(PurchaseRequest.class);
                    assert purchase != null;
                    System.out.println(purchase.clientId);
                    if(purchase.clientId.equals(clientID)){
                        purchaseList.add(purchase);
                        System.out.println("purchase added!");
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        dbCooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cooks.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Cook cook = postSnapshot.getValue(Cook.class);
                    assert cook != null;
                    cooks.add(cook);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        dbMeals.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                meals.clear();

                for (DataSnapshot dbCookMeal : dataSnapshot.getChildren()) {
                    for (DataSnapshot dbMeal: dbCookMeal.getChildren()){
                        Meal meal = dbMeal.getValue(Meal.class);
                        assert meal != null;
                        meals.add(meal);
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
        System.out.println(purchaseList.size());
        RecyclerViewClientPurchase adapter = new RecyclerViewClientPurchase(purchaseList, meals, cooks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewClientPurchase.setLayoutManager(layoutManager);
        recyclerViewClientPurchase.setItemAnimator(new DefaultItemAnimator());
        recyclerViewClientPurchase.setAdapter(adapter);
    }

    public void purchasesMenuBackBtnClick(){
        Intent i = new Intent(this, welcomeClient.class);
        Bundle bundle = new Bundle();
        bundle.putString("clientID", clientID);
        i.putExtras(bundle);
        startActivity(i);
    }
}