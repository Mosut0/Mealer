package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeAdmin extends Activity {

    Button logOutAdminBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeadmin);

        logOutAdminBtn = (Button) findViewById(R.id.logOutButtonAdmin);

        logOutAdminBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                adminLogOffClick();
            }
        });
    }

    public void adminLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
