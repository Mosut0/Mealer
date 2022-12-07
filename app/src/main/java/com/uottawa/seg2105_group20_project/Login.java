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

public class Login extends Activity {
    DatabaseReference dbClient;
    List<Client> clients;
    DatabaseReference dbCook;
    List<Cook> cookList;
    Button buttonLogin;
    Button signupBtn;
    Button adminBtn;
    EditText textEmail;
    EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textEmail = (EditText) findViewById(R.id.loginEmailAddress);
        textPassword = (EditText) findViewById(R.id.loginPassword);
        buttonLogin = (Button) findViewById(R.id.loginButton);
        signupBtn = (Button) findViewById(R.id.signupButton);
        adminBtn = (Button) findViewById(R.id.adminButton);

        clients = new ArrayList<>();
        cookList = new ArrayList<>();
        dbClient = FirebaseDatabase.getInstance().getReference("clients");
        dbCook = FirebaseDatabase.getInstance().getReference("cooks");

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkExistingUser();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                signUpOnClick();
            }
        });

        adminBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                adminOnClick();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        dbClient.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clients.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Client client = postSnapshot.getValue(Client.class);
                    clients.add(client);
                    System.out.println(client.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        dbCook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cookList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Cook cook = postSnapshot.getValue(Cook.class);
                    cookList.add(cook);
                    System.out.println(cook.email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }
    protected void checkExistingUser(){

        String Email = textEmail.getText().toString().trim();
        String Password = textPassword.getText().toString().trim();
        boolean loginFound = false;
        for (int i=0; i<clients.size(); i++){
            if (clients.get(i).email.trim().equals(Email) && clients.get(i).password.trim().equals(Password)){
                loginFound = true;
                Intent j = new Intent(this, welcomeClient.class);
                Bundle bundle = new Bundle();
                bundle.putString("clientID", clients.get(i).id.trim());
                j.putExtras(bundle);
                startActivity(j);
            };
        }
        for (int i=0; i<cookList.size(); i++){
            if (cookList.get(i).email.trim().equals(Email) && cookList.get(i).password.trim().equals(Password)){
                loginFound = true;
                Intent j = new Intent(this, welcomeCook.class);
                Bundle bundle = new Bundle();
                bundle.putString("cookID", cookList.get(i).id.trim());
                j.putExtras(bundle);
                startActivity(j);
            };
        }
        if (!loginFound){
            Toast.makeText(this, "Login not found", Toast.LENGTH_LONG).show();
        }

    }
    protected void signUpOnClick(){
        Intent i = new Intent(this, Type.class);
        startActivity(i);
    }
    protected void adminOnClick(){
        Intent i = new Intent(this, LoginAdmin.class);
        startActivity(i);
    }
}
