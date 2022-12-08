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

public class CookPurchaseRequest extends AppCompatActivity {

    List<PurchaseRequest> purchaseRequests;
    List<Meal> meals;
    List<Client> clients;

    RecyclerView recyclerViewPurchaseRequest;
    Button cookPurchaseBackBtn;

    DatabaseReference dbPurchaseRequests, dbMeals, dbClients;

    String cookID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_purchase_request);
        Bundle bundle = getIntent().getExtras();
        cookID = bundle.getString("cookID");

        purchaseRequests = new ArrayList<>();
        meals = new ArrayList<>();
        clients = new ArrayList<>();

        recyclerViewPurchaseRequest = findViewById(R.id.recyclerViewPurchaseRequest);
        cookPurchaseBackBtn = (Button) findViewById(R.id.cookPurchaseBackBtn);

        dbMeals = FirebaseDatabase.getInstance().getReference("meals");
        dbPurchaseRequests = FirebaseDatabase.getInstance().getReference("purchaseRequests");
        dbClients = FirebaseDatabase.getInstance().getReference("clients");

        cookPurchaseBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cookPurchaseBackBtnClick();
            }
        });

    }

    protected void onStart() {
        super.onStart();

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        dbClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clients.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Client client = postSnapshot.getValue(Client.class);
                    assert client != null;
                    clients.add(client);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        dbPurchaseRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                purchaseRequests.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PurchaseRequest purchase = postSnapshot.getValue(PurchaseRequest.class);
                    assert purchase != null;
                    if(purchase.status.equals("Pending") && purchase.cookId.equals(cookID)){
                        purchaseRequests.add(purchase);
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
        System.out.println(purchaseRequests.size());
        RecyclerAdapterPurchaseRequest adapter = new RecyclerAdapterPurchaseRequest(purchaseRequests, meals, clients);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPurchaseRequest.setLayoutManager(layoutManager);
        recyclerViewPurchaseRequest.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPurchaseRequest.setAdapter(adapter);
    }

    public void cookPurchaseBackBtnClick(){
        Intent i = new Intent(this, welcomeCook.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }

}