package edu.gatech.cleanwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cleanwater.Model.User;
import edu.gatech.cleanwater.Model.UserType;
import edu.gatech.cleanwater.R;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private DatabaseReference myRef;

    private EditText etName;
    private EditText etAddress;
    private Button bUpdate;
    private TextView tvUsername;
    private Button bLogout;
    private Button bSubmit;
    private Button bView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        myRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mAuth.getCurrentUser();

        etName = (EditText) findViewById(R.id.etName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        bUpdate = (Button) findViewById(R.id.bUpdate);
        tvUsername = (TextView) findViewById(R.id.tvEmail);
        bLogout = (Button) findViewById(R.id.bLogout);
        bSubmit = (Button) findViewById(R.id.bSubmit);
        bView = (Button) findViewById(R.id.bView);

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

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfo();
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent report = new Intent(ProfileActivity.this, ReportActivity.class);
                ProfileActivity.this.startActivity(report);
            }
        });

        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(ProfileActivity.this, DrawerActivity.class);
                startActivity(view);
            }
        });
    }

    /**
     * Saves the inputted data to the database
     */
    private void saveInfo() {
        String name = etName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        User updateUser = new User(name, address, UserType.ADMIN.name());

        FirebaseUser fUser = mAuth.getCurrentUser();

        myRef.child(fUser.getUid()).setValue(updateUser);

        Toast.makeText(this, "User Updated", Toast.LENGTH_LONG).show();
    }
}
