package com.uottawa.seg2105_group20_project;

import static com.uottawa.seg2105_group20_project.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MenuItem;
import android.widget.PopupMenu;

//Class for sign up client page
public class SignUpClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_signup_client);

        EditText firstName = (EditText) findViewById(id.firstname);

        EditText lastName = (EditText) findViewById(id.lastname);

        EditText emailAddress = (EditText) findViewById(id.emailaddress);

        EditText password = (EditText) findViewById(id.password);

        EditText retypePassword = (EditText) findViewById(id.repassword);

        Button signupButton = (Button) findViewById(id.signupbtn);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = password.getText().toString();
                String repwd = retypePassword.getText().toString();


                if (pwd != repwd) {
                    Toast.makeText(SignUpClient.this, "Password do not match", Toast.LENGTH_SHORT);
                    password.setText(null);
                    retypePassword.setText(null);
                } else {
                    //Save User info to database

                    //Display successful signup message
                    Toast.makeText(SignUpClient.this, "Signup Successful!", Toast.LENGTH_SHORT);

                    //Ask if he wants to sign up as a client or cook

                    PopupMenu popupMenu = new PopupMenu(SignUpClient.this,signupButton);

                    popupMenu.getMenuInflater().inflate(menu.popup_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        
                        public boolean onMenuItemClick(MenuItem item) {

                            Toast.makeText(SignUpClient.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                            return true;

                        }
                    });

                    popupMenu.show();

                    //If item selected is client
//\\                    if(){
//                        //redirect to client page
//                    }else{
//                        //else redirect to cook page
//                    }
                }

            }
        });


    }
}