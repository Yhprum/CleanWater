package edu.gatech.cleanwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.cleanwater.R;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView tvUsername;
    private Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        bLogout = (Button) findViewById(R.id.bLogout);

        tvUsername.setText("Welcome " + user.getEmail());

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                Intent logout = new Intent(ProfileActivity.this, WelcomeActivity.class);
                ProfileActivity.this.startActivity(logout);
            }
        });
    }
}
