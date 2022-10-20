package com.uottawa.seg2105_group20_project;

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

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seg2105-group20-default-rtdb.firebaseio.com");

    private Button adminButton;
    private Button signupButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button adminButton = findViewById(R.id.adminButton);
        final Button signupButton = findViewById(R.id.signupButton);
        final Button loginButton = findViewById(R.id.loginButton);

        final EditText loginEmailAddress = findViewById(R.id.loginEmailAddress);
        final EditText loginPassword = findViewById(R.id.loginPassword);

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
//
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailAddressEntered = loginEmailAddress.getText().toString();

                final String passwordEntered = loginPassword.getText().toString();

                if(emailAddressEntered.isEmpty() || passwordEntered.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //Check if email address exists in firebase database
                            if(snapshot.hasChild(emailAddressEntered)){
                                //email exists in firebase database
                                //now get the password and match it

                                final String getPasswowrd = snapshot.child(emailAddressEntered).child("password").getValue(String.class);

                                //Check if password also matches email in database
                                if(getPasswowrd.equals(passwordEntered)){
                                    loginClick(view);
                                } else {
                                    Toast.makeText(MainActivity.this,"Wrong Password entered", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this,"Wrong Email Address entered", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });


    }

    public void adminClick (View v){
        startActivity(new Intent(this, LoginAdmin.class));
    }

    public void signUpClick (View v) {
        startActivity(new Intent(this, SignupRole.class));
    }

    public void loginClick (View v){
        startActivity(new Intent(this, WelcomePage.class));
    }

}