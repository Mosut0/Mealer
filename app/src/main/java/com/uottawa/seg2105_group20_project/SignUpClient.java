package com.uottawa.seg2105_group20_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.text.TextUtils;

import android.widget.Toast;

//Class for sign up cook page
public class SignUpClient extends AppCompatActivity {

    DatabaseReference databaseClients;

    EditText editClientFirst, editClientLast, editClientEmail,
            editClientPassword, editClientAddress, editClientCardNumber,
            editClientExpiration, editClientCVV;

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
        editClientExpiration = findViewById(R.id.clientExpirationDate);
        editClientCVV = findViewById(R.id.clientCVV);

        clientSignUpBtn = findViewById(R.id.clientSignupBtn);

        clients = new ArrayList<>();
        databaseClients = FirebaseDatabase.getInstance().getReference("cooks");
        clientSignUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addClient();
            }
        });

        clientBackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                onBackClick(view);
            }
        });


    }

    private void addClient(){
        String firstName = editClientFirst.getText().toString().trim();
        String lastName = editClientLast.getText().toString().trim();
        String email = editClientEmail.getText().toString().trim();
        String password = editClientPassword.getText().toString().trim();
        String address = editClientAddress.getText().toString().trim();
        String cardNumber = editClientCardNumber.getText().toString().trim();
        String expirationDate = editClientExpiration.getText().toString().trim();
        String cvv = editClientCVV.getText().toString().trim();

        CreditCard card;
        Client newClient;
        Intent intent;

        if(!(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password)
                && TextUtils.isEmpty(address) && TextUtils.isEmpty(cardNumber) && TextUtils.isEmpty(expirationDate) && TextUtils.isEmpty(cvv))){
            try {
                Integer.parseInt(cardNumber);
                Integer.parseInt(expirationDate);
                Integer.parseInt(cvv);
            }catch (NumberFormatException e){
                Toast.makeText(this, "Invalid Credit Card Information!", Toast.LENGTH_LONG).show();
                return;
            }

            String dbID = databaseClients.push().getKey();

            card = new CreditCard(cardNumber, expirationDate, cvv);
            newClient = new Client(dbID, firstName, lastName, email, password, address, card);

            databaseClients.child(dbID).setValue(newClient);

            editClientFirst.setText("");
            editClientLast.setText("");
            editClientEmail.setText("");
            editClientPassword.setText("");
            editClientAddress.setText("");
            editClientCardNumber.setText("");
            editClientExpiration.setText("");
            editClientCVV.setText("");

            intent = new Intent(this, SuccessfulSignUp.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "One or more fields are empty!", Toast.LENGTH_LONG).show();
        }

    }

    private void onBackClick(View v){
        startActivity(new Intent(this, SignUpRole.class));
    }

}