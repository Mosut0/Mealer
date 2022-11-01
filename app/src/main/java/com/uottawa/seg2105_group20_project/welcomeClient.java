package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeClient extends Activity{

    Button logOutClientBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeclient);

        logOutClientBtn = (Button) findViewById(R.id.logOutButtonClient);

        logOutClientBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clientLogOffClick();
            }
        });
    }

    public void clientLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
