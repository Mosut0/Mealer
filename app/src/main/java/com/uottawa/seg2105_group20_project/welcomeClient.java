package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeClient extends Activity{

    String clientID;
    Button logOutClientBtn, searchForMealBtn, viewPurchasesBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeclient);

        Bundle bundle = getIntent().getExtras();
        clientID = bundle.getString("clientID");

        logOutClientBtn = (Button) findViewById(R.id.logOutButtonClient);
        searchForMealBtn = (Button) findViewById(R.id.searchMealBtn);
        viewPurchasesBtn = (Button) findViewById(R.id.viewPurchasesbtn);

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

    public void clientLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
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
