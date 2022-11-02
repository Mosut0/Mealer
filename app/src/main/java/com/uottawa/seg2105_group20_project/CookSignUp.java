package com.uottawa.seg2105_group20_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CookSignUp extends AppCompatActivity{

    EditText textLastName;
    EditText textFirstName;
    EditText textEmail;
    EditText textPassword;
    EditText textAddress;
    EditText textDescription;

    Button buttonCookSignUp;
    Button backBtn;

    List<Cook> cooks;
    DatabaseReference databaseCook;

    Button btn2;
    int SELECT_IMAGE_CODE=1;
    boolean voidChequeUploaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooksignup);
//
        textFirstName = (EditText) findViewById(R.id.cookFirstName);
        textLastName = (EditText) findViewById(R.id.cookLastName);
        textEmail = (EditText) findViewById(R.id.cookEmailAddress);
        textPassword = (EditText) findViewById(R.id.cookPassword);
        textAddress = (EditText) findViewById(R.id.cookAddress);
        textDescription = (EditText) findViewById(R.id.cookDescription);
        buttonCookSignUp = (Button) findViewById(R.id.cookSignupBtn);
        backBtn = (Button) findViewById(R.id.cookBackBtn);

        btn2 = findViewById(R.id.cookVoidCheque);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);

            }
        });

        cooks = new ArrayList<>();
        databaseCook = FirebaseDatabase.getInstance().getReference("cooks");
        buttonCookSignUp.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                addCook();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                backOnClick();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resutltCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resutltCode, data);

        if(requestCode == 1){

            try{
                Uri uri = data.getData();
                btn2.setText("Image Uploaded");
                voidChequeUploaded = true;
            }
            catch (Exception e){
                btn2.setText("Image not Uploaded, try again");
            }
        }

    }

    private void addCook() {
        String firstName = textFirstName.getText().toString().trim();
        String lastName = textLastName.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        String address = textAddress.getText().toString().trim();
        String description = textDescription.getText().toString().trim();

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(description) && voidChequeUploaded == true){
            String id = databaseCook.push().getKey();
            String voidCheque ="test";
            Cook cook = new Cook(id, firstName, lastName, email, password, address, description, voidCheque);

            databaseCook.child(id).setValue(cook);

            textFirstName.setText("");
            textLastName.setText("");
            textEmail.setText("");
            textPassword.setText("");
            textAddress.setText("");
            textDescription.setText("");

            Toast.makeText(this, "Successfully Signed Up!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        else if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(description) && voidChequeUploaded == false){
            Toast.makeText(this, "Please Upload a Void Cheque", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "One or Many Fields Are Empty", Toast.LENGTH_LONG).show();
        }

    }

    private void backOnClick(){
        Intent i = new Intent(this, Type.class);
        startActivity(i);
    }
}
