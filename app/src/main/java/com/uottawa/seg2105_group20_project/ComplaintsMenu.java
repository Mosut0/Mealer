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

public class ComplaintsMenu extends AppCompatActivity {

    DatabaseReference dbComplaints;
    List<Complaint> complaintList;
    RecyclerView recyclerView;

    Button complaintMenuBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_menu);
        recyclerView = findViewById(R.id.recyclerView);
        complaintList = new ArrayList<>();
        dbComplaints = FirebaseDatabase.getInstance().getReference("complaints");
        complaintMenuBackBtn = (Button) findViewById(R.id.complaintsBackBtn);

        complaintMenuBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                complaintBackClick();
            }
        });





    }

    protected void onStart() {
        super.onStart();

        dbComplaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaintList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Complaint complaint = postSnapshot.getValue(Complaint.class);
                    if(complaint != null && !complaint.isReviewed()) { //Don't remember why I added a reviewed attribute
                        complaint.setDbId(postSnapshot.getKey());
                        complaintList.add(complaint);
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
        System.out.println(complaintList.size());
        RecyclerAdapterComplaint adapter = new RecyclerAdapterComplaint(complaintList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void complaintBackClick(){
        Intent i = new Intent(this, welcomeAdmin.class);
        startActivity(i);
    }
}