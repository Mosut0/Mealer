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

//Class for main login page
public class MainActivity extends AppCompatActivity {

    protected Button adminButton, signupButton, loginButton;
    protected EditText editLoginEmail, editLoginPassword;
    protected DatabaseReference databaseAdmins, databaseClients, databaseCooks;
    protected ArrayList<Client> clients;
    protected ArrayList<Cook> cooks;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminButton = findViewById(R.id.adminButton);
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);

        editLoginEmail = findViewById(R.id.loginEmailAddress);
        editLoginPassword = findViewById(R.id.loginPassword);

        databaseAdmins = FirebaseDatabase.getInstance().getReference("admins");
        databaseClients = FirebaseDatabase.getInstance().getReference("clients");
        databaseCooks = FirebaseDatabase.getInstance().getReference("cooks");

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminClick(view);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpClick(view);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserExist();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clients.clear();

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Client client = postSnapshot.getValue(Client.class);
                    clients.add(client);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cooks.clear();

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Cook cook = postSnapshot.getValue(Cook.class);
                    cooks.add(cook);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkUserExist(){
        String email = editLoginEmail.getText().toString().trim();
        String password = editLoginPassword.getText().toString().trim();
        Intent intent = null;
        boolean userExist = false;
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getEmail().trim().equals(email) && clients.get(i).getPassword().trim().equals(password)){
                intent = new Intent(this, WelcomePage.class);
                startActivity(intent);
                userExist = true;
            }
        }
        for(int i = 0; i < cooks.size(); i++){
            if(cooks.get(i).getEmail().trim().equals(email) && cooks.get(i).getPassword().trim().equals(password)){
                intent = new Intent(this, WelcomePage.class);
                startActivity(intent);
                userExist = true;
            }
        }
        if(!userExist){
            Toast.makeText(this, "Login Not found!", Toast.LENGTH_LONG).show();
        }
    }

    public void adminClick (View v){
        startActivity(new Intent(this, LoginAdmin.class));
    }

    public void signUpClick (View v) {
        startActivity(new Intent(this, SignUpRole.class));
    }

}