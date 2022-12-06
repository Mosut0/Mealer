package com.uottawa.seg2105_group20_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CookProfile extends AppCompatActivity {
    DatabaseReference dbCook;
    String cookID, cookFullName, cookEmail, cookAddress, cookSales, cookRating;
    TextView cookProfileFullName, cookProfileEmail, cookProfileAddress, cookProfileSales, cookProfileRating;
    Button cookProfileBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_profile);
        Bundle bundle = getIntent().getExtras();
        cookID = bundle.getString("cookID");
        dbCook = FirebaseDatabase.getInstance().getReference("cooks");
        cookProfileFullName = (TextView) findViewById(R.id.cookProfileFullName);
        cookProfileEmail = (TextView) findViewById(R.id.cookProfileEmail);
        cookProfileAddress = (TextView) findViewById(R.id.cookProfileAddress);
        cookProfileSales = (TextView) findViewById(R.id.cookProfileSales);
        cookProfileRating = (TextView) findViewById(R.id.cookProfileRating);
        cookProfileBackBtn = (Button) findViewById(R.id.cookProfileBack);

        cookProfileBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cookProfileBackBtnClick();
            }
        });
    }

    protected void onStart() {
        super.onStart();

        dbCook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Cook cook = postSnapshot.getValue(Cook.class);
                    assert cook != null;
                    if(cook.id.equals(cookID)){
                        cookFullName = cook.firstName + " " + cook.lastName;
                        cookEmail = cook.email;
                        cookAddress = cook.address;
                        cookSales = String.valueOf(cook.sales);
                        cookRating = String.valueOf(cook.rating);
                        break;
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
                setProfileInfo();
            }
        }, 500);
    }

    private void setProfileInfo(){
        cookProfileFullName.setText(Html.fromHtml("<b>" + "Full Name: " + "</b>" + cookFullName));
        cookProfileEmail.setText(Html.fromHtml("<b>" + "Email: " + "</b>" + cookEmail));
        cookProfileAddress.setText(Html.fromHtml("<b>" + "Address: " + "</b>" + cookAddress));
        cookProfileSales.setText(Html.fromHtml("<b>" + "Meals Sold: " + "</b>" + cookSales));
        cookProfileRating.setText(Html.fromHtml("<b>" + "Your Rating: " + "</b>" + cookRating + "/5"));

    }

    public void cookProfileBackBtnClick(){
        Intent i = new Intent(this, welcomeCook.class);
        Bundle bundle = new Bundle();
        bundle.putString("cookID", cookID);
        i.putExtras(bundle);
        startActivity(i);
    }
}