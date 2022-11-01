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

    EditText editTextLastName;
    EditText editTextFirstName;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextAddress;
    EditText editTextCreditCardNumber;

    Button buttonClientSignUp;
    Button buttonClientBack;


    List<Client> clients;
    DatabaseReference databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientsignup);

        editTextFirstName = (EditText) findViewById(R.id.clientFirstName);
        editTextLastName = (EditText) findViewById(R.id.clientLastName);
        editTextEmail = (EditText) findViewById(R.id.clientEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.clientPassword);
        editTextAddress = (EditText) findViewById(R.id.clientAddress);
        editTextCreditCardNumber = (EditText) findViewById(R.id.clientCreditCardInfo);
        buttonClientSignUp = (Button) findViewById(R.id.clientSignupBtn);
        buttonClientBack = (Button) findViewById(R.id.clientBackBtn);
        clients = new ArrayList<>();
        databaseClient = FirebaseDatabase.getInstance().getReference("clients");
        //adding an onclicklistener to button
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
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String creditCardNumber = editTextCreditCardNumber.getText().toString().trim();
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef2 = database.getReference("First Name");
        //myRef2.setValue( editTextFirstName.getText().toString().trim());

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(creditCardNumber)){
            String id = databaseClient.push().getKey();

            Client client = new Client(id, firstName, lastName, email, password, address, creditCardNumber);

            databaseClient.child(id).setValue(client);

            editTextFirstName.setText("");
            editTextLastName.setText("");
            editTextEmail.setText("");
            editTextPassword.setText("");
            editTextAddress.setText("");
            editTextCreditCardNumber.setText("");

            Toast.makeText(this, "Sign up Successful", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_LONG).show();
        }

    }

    private void backOnClick(){
        Intent i = new Intent(this, Type.class);
        startActivity(i);
    }

}
