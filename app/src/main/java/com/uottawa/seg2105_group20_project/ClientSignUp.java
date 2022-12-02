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


public class ClientSignUp extends Activity{

    EditText textLastName;
    EditText textFirstName;
    EditText textEmail;
    EditText editPassword;
    EditText textAddress;
    EditText textCreditCardNumber;
    EditText textCVV;
    EditText textExpiry;

    Button buttonClientSignUp;
    Button buttonClientBack;


    List<Client> clients;
    DatabaseReference databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientsignup);

        textFirstName = (EditText) findViewById(R.id.clientFirstName);
        textLastName = (EditText) findViewById(R.id.clientLastName);
        textEmail = (EditText) findViewById(R.id.clientEmailAddress);
        editPassword = (EditText) findViewById(R.id.clientPassword);
        textAddress = (EditText) findViewById(R.id.clientAddress);
        textCreditCardNumber = (EditText) findViewById(R.id.clientCreditCardInfo);
        textCVV = (EditText) findViewById(R.id.CVV);
        textExpiry = (EditText) findViewById(R.id.expiryDate);
        buttonClientSignUp = (Button) findViewById(R.id.clientSignupBtn);
        buttonClientBack = (Button) findViewById(R.id.clientBackBtn);
        clients = new ArrayList<>();
        databaseClient = FirebaseDatabase.getInstance().getReference("clients");
        buttonClientSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addClient();
            }
        });

        buttonClientBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                backOnClick();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseClient.addValueEventListener(new ValueEventListener() {
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

    private void addClient() {
        String firstName = textFirstName.getText().toString().trim();
        String lastName = textLastName.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String address = textAddress.getText().toString().trim();
        String creditCardNumber = textCreditCardNumber.getText().toString().trim();
        String cvv = textCVV.getText().toString().trim();
        String expiry = textExpiry.getText().toString().trim();

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(creditCardNumber)&& !TextUtils.isEmpty(cvv) && !TextUtils.isEmpty(expiry)){
            String id = databaseClient.push().getKey();

            Client client = new Client(id, firstName, lastName, email, password, address, creditCardNumber, cvv, expiry);

            databaseClient.child(id).setValue(client);

            textFirstName.setText("");
            textLastName.setText("");
            textEmail.setText("");
            editPassword.setText("");
            textAddress.setText("");
            textCreditCardNumber.setText("");

            Toast.makeText(this, "Successfully Signed Up!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "One or Many Field Are Missing", Toast.LENGTH_LONG).show();
        }

    }

    private void backOnClick(){
        Intent i = new Intent(this, Type.class);
        startActivity(i);
    }

}
