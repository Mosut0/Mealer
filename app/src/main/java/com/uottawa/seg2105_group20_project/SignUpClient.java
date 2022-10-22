package com.uottawa.seg2105_group20_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;
import android.widget.Toast;

//Class for sign up cook page
public class SignUpClient extends AppCompatActivity {

    DatabaseReference databaseClients;

    EditText editClientFirst, editClientLast, editClientEmail,
            editClientPassword, editClientAddress, editClientCardNumber;

    Button clientBackBtn, clientSignUpBtn;

    ArrayList<Client> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_client);

        editClientFirst = findViewById(R.id.clientFirstName);
        editClientLast = findViewById(R.id.clientLastName);
        editClientAddress = findViewById(R.id.clientEmailAddress);
        editClientPassword = findViewById(R.id.clientPassword);
        editClientAddress = findViewById(R.id.clientAddress);
        editClientCardNumber = findViewById(R.id.clientCreditCardInfo);

        clientBackBtn = findViewById(R.id.clientBackBtn);
        clientSignUpBtn = findViewById(R.id.clientSignupBtn);

        clients = new ArrayList<>();
        databaseClients = FirebaseDatabase.getInstance().getReference("clients");

        clientSignUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addClient(view);
            }
        });

        clientBackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                onBackClick(view);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseClients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clients.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Client client = postSnapshot.getValue(Client.class);
                    clients.add(client);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    public void addClient(View v){
        String firstName = editClientFirst.getText().toString().trim();
        String lastName = editClientLast.getText().toString().trim();
        String email = editClientEmail.getText().toString().trim();
        String password = editClientPassword.getText().toString().trim();
        String address = editClientAddress.getText().toString().trim();
        String cardNumber = editClientCardNumber.getText().toString().trim();

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(cardNumber)){

            String dbID = databaseClients.push().getKey();

            Client newClient = new Client(dbID, firstName, lastName, email, password, address, cardNumber);

            databaseClients.child(dbID).setValue(newClient);

            editClientFirst.setText("");
            editClientLast.setText("");
            editClientEmail.setText("");
            editClientPassword.setText("");
            editClientAddress.setText("");
            editClientCardNumber.setText("");

            Toast.makeText(this, "Sign Up Successful!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "One or more fields are empty!", Toast.LENGTH_LONG).show();
        }

    }

    public void onBackClick(View v){
        startActivity(new Intent(this, SignUpRole.class));
    }

}