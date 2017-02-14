package edu.gatech.cleanwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.cleanwater.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(WelcomeActivity.this, RegisterActivity.class);
                WelcomeActivity.this.startActivity(cancel);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(WelcomeActivity.this, LoginActivity.class);
                WelcomeActivity.this.startActivity(cancel);
            }
        });
    }
}
