package com.uottawa.seg2105_group20_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class ComplaintsMenu extends AppCompatActivity {

    private ArrayList<Complaint> complaintList;
    private RecyclerView recyclerView;

    Button complaintMenuBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_menu);
        recyclerView = findViewById(R.id.recyclerView);
        complaintList = new ArrayList<>();

        complaintMenuBackBtn = (Button) findViewById(R.id.complaintsBackBtn);

        setComplainInfo();
        setAdapter();

        complaintMenuBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                complaintBackClick();
            }
        });
    }

    public void setAdapter(){
        RecyclerAdapter adapter = new RecyclerAdapter(complaintList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setComplainInfo(){
        complaintList.add(new Complaint("Mostafa", "Adham","testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest"));
        complaintList.add(new Complaint("Adham", "Mohammad","test"));
        complaintList.add(new Complaint("Mohammed", "Mai","Test"));
        complaintList.add(new Complaint("Mai", "Mostafa","Test"));
        complaintList.add(new Complaint("Mostafa", "Adham","testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest"));
        complaintList.add(new Complaint("Adham", "Mohammad","test"));
        complaintList.add(new Complaint("Mohammed", "Mai","Test"));
        complaintList.add(new Complaint("Mai", "Mostafa","Test"));
        complaintList.add(new Complaint("Mostafa", "Adham","testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest"));
        complaintList.add(new Complaint("Adham", "Mohammad","test"));
        complaintList.add(new Complaint("Mohammed", "Mai","Test"));
        complaintList.add(new Complaint("Mai", "Mostafa","Test"));
        complaintList.add(new Complaint("Mostafa", "Adham","testtesttesttesttesttesttesttesttesttest"));
        complaintList.add(new Complaint("Adham", "Mohammad","test"));
        complaintList.add(new Complaint("Mohammed", "Mai","Test"));
        complaintList.add(new Complaint("Mai", "Mostafa","Test"));
    }

    public void complaintBackClick(){
        Intent i = new Intent(this, welcomeAdmin.class);
        startActivity(i);
    }
}