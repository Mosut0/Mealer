package com.uottawa.seg2105_group20_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class welcomeClient extends Activity{

    String clientID;
    Button logOutClientBtn, searchForMealBtn, viewPurchasesBtn;
    TextView clientNotificationText;
    List<PurchaseRequest> purchaseRequestList;
    DatabaseReference dbPurchaseRequest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeclient);

        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getString("clientID");

        logOutClientBtn = (Button) findViewById(R.id.logOutButtonClient);
        searchForMealBtn = (Button) findViewById(R.id.searchMealBtn);
        viewPurchasesBtn = (Button) findViewById(R.id.viewPurchasesbtn);
        clientNotificationText = (TextView) findViewById(R.id.clientNotificationText);
        clientNotificationText.setVisibility(View.INVISIBLE);

        purchaseRequestList = new ArrayList<>();

        dbPurchaseRequest = FirebaseDatabase.getInstance().getReference("purchaseRequests");

        logOutClientBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clientLogOffClick();
            }
        });

        searchForMealBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchForMealBtnClick();
            }
        });

        viewPurchasesBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewPurchasesBtnClick();
            }
        });

    }

    protected void onStart() {
        super.onStart();

        dbPurchaseRequest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                purchaseRequestList.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    PurchaseRequest purchaseRequest = postSnapshot.getValue(PurchaseRequest.class);
                    assert purchaseRequest != null;
                    if (purchaseRequest.clientId.equals(clientID)){
                        purchaseRequestList.add(purchaseRequest);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendNotification(purchaseRequestList);
            }
        }, 1500);

    }

    public void clientLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    public void sendNotification(List<PurchaseRequest> purchaseRequests){
        for (int i=0; i<purchaseRequests.size(); i++){
            if (!purchaseRequests.get(i).status.equals("Pending") && !purchaseRequests.get(i).notificationSent){
                dbPurchaseRequest.child(purchaseRequests.get(i).id).child("notificationSent").setValue(true);
                if (purchaseRequests.get(i).status.equals("Rejected")){
                    clientNotificationText.setText("NOTIFICATION: A Purchase Request Has been Rejected!\nClick View Purchases");
                }
                final Handler handler = new Handler();
                clientNotificationText.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clientNotificationText.setVisibility(View.INVISIBLE);
                    }
                }, 8000);
                break;
            }
        }
    }

    public void searchForMealBtnClick () {
        Intent i = new Intent(this, SearchForMeal.class);
        Bundle bundle = new Bundle();
        bundle.putString("clientID", clientID);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void viewPurchasesBtnClick () {
        Intent i = new Intent(this, ClientPurchase.class);
        Bundle bundle = new Bundle();
        bundle.putString("clientID", clientID);
        i.putExtras(bundle);
        startActivity(i);
    }
}
