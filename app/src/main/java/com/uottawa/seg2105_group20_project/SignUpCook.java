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
public class SignUpCook extends AppCompatActivity {

    DatabaseReference databaseCooks;

    EditText editCookFirst, editCookLast, editCookEmail,
            editCookPassword, editCookAddress, editCookDescription;

    Button cookBackBtn, cookSignUpBtn, voidChequeBtn;

    ArrayList<Cook> cooks;

    int SELECT_IMAGE_CODE = 1;

    boolean uploadedCheque = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_cook);

        editCookFirst = findViewById(R.id.cookFirstName);
        editCookLast = findViewById(R.id.cookLastName);
        editCookEmail = findViewById(R.id.cookEmailAddress);
        editCookPassword = findViewById(R.id.cookPassword);
        editCookAddress = findViewById(R.id.cookAddress);
        editCookDescription = findViewById(R.id.cookDescription);

        cookBackBtn = findViewById(R.id.cookBackBtn);
        cookSignUpBtn = findViewById(R.id.cookSignupBtn);
        voidChequeBtn = findViewById(R.id.cookVoidCheque);

        cooks = new ArrayList<>();
        databaseCooks = FirebaseDatabase.getInstance().getReference("cooks");
        cookSignUpBtn.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
                addCook();
            }
        });

        voidChequeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Title"), SELECT_IMAGE_CODE);
            }
        });

        cookBackBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                onBackClick();
            }
        });


    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            try{
                Uri uri = data.getData();
                voidChequeBtn.setText("Image Upload Successful!");
                uploadedCheque = true;
            }catch (Exception e){
                voidChequeBtn.setText("Image Not Uploaded. Try Again.");
            }
        }
    }

    private void addCook(){
        String firstName = editCookFirst.getText().toString().trim();
        String lastName = editCookLast.getText().toString().trim();
        String email = editCookEmail.getText().toString().trim();
        String password = editCookPassword.getText().toString().trim();
        String address = editCookAddress.getText().toString().trim();
        String description = editCookDescription.getText().toString().trim();

        if(!(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(address) && TextUtils.isEmpty(description)) && uploadedCheque) {
            String dbID = databaseCooks.push().getKey();
            String voidCheque = "Cheque Uploaded";
            Cook newCook = new Cook(dbID, firstName, lastName, email, password, address, description, voidCheque);

            databaseCooks.child(dbID).setValue(newCook);

            editCookFirst.setText("");
            editCookLast.setText("");
            editCookEmail.setText("");
            editCookPassword.setText("");
            editCookAddress.setText("");
            editCookDescription.setText("");

            Intent intent = new Intent(this, SuccessfulSignUp.class);
            startActivity(intent);
        }else if(!uploadedCheque){
            Toast.makeText(this, "Please Upload a Void Cheque!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "One or more fields are empty!", Toast.LENGTH_LONG).show();
        }

    }

    private void onBackClick(){
        startActivity(new Intent(this, SignUpRole.class));
    }

}