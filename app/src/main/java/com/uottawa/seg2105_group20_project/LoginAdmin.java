package com.uottawa.seg2105_group20_project;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Class for admin login page
public class LoginAdmin extends AppCompatActivity {

    DatabaseReference databaseAdmins;
    ArrayList<Admin> admins;
    Button loginAdminButton, backButton;
    EditText editAdminEmail, editAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        editAdminEmail = findViewById(R.id.loginEmailAddressAdmin);
        editAdminPassword = findViewById(R.id.loginPasswordAdmin);
        loginAdminButton = findViewById(R.id.loginButtonAdmin);
        databaseAdmins = FirebaseDatabase.getInstance().getReference("admins");
        admins = new ArrayList<>();
        backButton = findViewById(R.id.backButtonAdmin);

        loginAdminButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                checkUserExist();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                onBackClick(view);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        databaseAdmins.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admins.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Admin admin = postSnapshot.getValue(Admin.class);
                    admins.add(admin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void checkUserExist(){
        String email = editAdminEmail.getText().toString().trim();
        String password = editAdminPassword.getText().toString().trim();
        boolean userExist = false;
        for(int i = 0; i < admins.size(); i++){
            if(admins.get(i).userName.trim().equals(email) && admins.get(i).password.trim().equals(password)){
                Intent intent = new Intent(this, WelcomePageAdmin.class);
                startActivity(intent);
                userExist = true;
            }
        }
        if(!userExist){
            Toast.makeText(this, "Login Not found!", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackClick(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}