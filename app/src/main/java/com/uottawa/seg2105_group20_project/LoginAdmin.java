package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginAdmin extends Activity {
    DatabaseReference dbAdmin;
    List<Admin> adminList;
    Button loginBtn;
    Button backBtn;
    EditText emailText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);
        emailText = (EditText) findViewById(R.id.loginEmailAddressAdmin);
        passwordText = (EditText) findViewById(R.id.loginPasswordAdmin);
        loginBtn = (Button) findViewById(R.id.loginButtonAdmin);
        backBtn = (Button) findViewById(R.id.backButtonAdmin);
        adminList = new ArrayList<>();
        dbAdmin = FirebaseDatabase.getInstance().getReference("Admin");

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkExistingUserAdmin();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                backOnClick();
            }
        });
    }

    protected void onStart() {
        super.onStart();

        dbAdmin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Admin admin = postSnapshot.getValue(Admin.class);
                    adminList.add(admin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
    protected void checkExistingUserAdmin(){

        String Email = emailText.getText().toString().trim();
        String Password = passwordText.getText().toString().trim();
        boolean loginFound = false;

        for (int i=0; i<adminList.size(); i++){
            if (adminList.get(i).email.trim().equals(Email) && adminList.get(i).password.trim().equals(Password)){
                Intent j = new Intent(this, welcomeAdmin.class);
                startActivity(j);
                loginFound = true;
            };
        }
        if (!loginFound){
            Toast.makeText(this, "Login not found", Toast.LENGTH_LONG).show();
        }

    }

    protected void backOnClick(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
