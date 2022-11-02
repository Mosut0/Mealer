package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeAdmin extends Activity {

    Button logOutAdminBtn;
    Button viewComplaintBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeadmin);

        logOutAdminBtn = (Button) findViewById(R.id.logOutButtonAdmin);
        viewComplaintBtn = (Button) findViewById(R.id.viewComplaintsBtn);

        logOutAdminBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                adminLogOffClick();
            }
        });

        viewComplaintBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewComplaintClick();
            }
        });
    }

    public void adminLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void viewComplaintClick () {
        Intent i = new Intent(this, ComplaintsMenu.class);
        startActivity(i);
    }
}
