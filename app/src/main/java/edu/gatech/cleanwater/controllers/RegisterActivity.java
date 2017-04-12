package edu.gatech.cleanwater.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cleanwater.Model.FirebaseHelper;
import edu.gatech.cleanwater.Model.User;
import edu.gatech.cleanwater.Model.UserType;
import edu.gatech.cleanwater.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Spinner sType;
    private ProgressDialog pdLoad;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        pdLoad = new ProgressDialog(this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        sType = (Spinner) findViewById(R.id.sType);

        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bCancel = (Button) findViewById(R.id.bCancel);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sType.setAdapter(adapter);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(RegisterActivity.this, WelcomeActivity.class);
                RegisterActivity.this.startActivity(cancel);
            }
        });
    }

    /**
     * Registers a new user via Firebase
     */
    private void registerUser() {
        final String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        final UserType type = (UserType) sType.getSelectedItem();

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Enter an E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        pdLoad.setMessage("Registering...");
        pdLoad.show();

        String s  = FirebaseHelper.registerUser(username, password);

        if (s.equals("bad username")) {
            pdLoad.dismiss();
            Toast.makeText(this, "Enter an E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (s.equals("bad password")) {
            pdLoad.dismiss();
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (s.equals("bad")) {
            pdLoad.dismiss();
            Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    pdLoad.dismiss();
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    mRef = FirebaseDatabase.getInstance().getReference();
                    User createUser = new User("enter name", "enter address", type.name());
                    FirebaseUser fUser = mAuth.getCurrentUser();
                    mRef.child(fUser.getUid()).setValue(createUser);
                } else {
                    pdLoad.dismiss();
                    pdLoad.dismiss();
                    Toast.makeText(RegisterActivity.this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
