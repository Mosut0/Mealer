package com.uottawa.seg2105_group20_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class welcomeCook extends Activity{

    Button logOutCookBtn;
    TextView cookStatusText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomecook);
        logOutCookBtn = (Button) findViewById(R.id.logOutButtonCook);
        cookStatusText = (TextView) findViewById(R.id.cookStatusText);

        logOutCookBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cookLogOffClick();
            }
        });
    }

    public void cookLogOffClick () {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
