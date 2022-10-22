package com.uottawa.seg2105_group20_project;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    private void checkUserExist(){
        String email = editAdminEmail.getText().toString().trim();
        String password = editAdminPassword.getText().toString().trim();
        Intent intent = null;
        boolean userExist = false;
        for(int i = 0; i < admins.size(); i++){
            if(admins.get(i).getEmail().trim().equals(email) && admins.get(i).getPassword().trim().equals(password)){
                intent = new Intent(this, WelcomePage.class);
                startActivity(intent);
                userExist = true;
            }
        }
        if(!userExist){
            Toast.makeText(this, "Login Not found!", Toast.LENGTH_LONG).show();
        }
    }

    private void onBackClick(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}