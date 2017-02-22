package edu.gatech.cleanwater.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cleanwater.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private ProgressDialog pdLoad;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        pdLoad = new ProgressDialog(this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final Button bCancel = (Button) findViewById(R.id.bCancel);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(LoginActivity.this, WelcomeActivity.class);
                LoginActivity.this.startActivity(cancel);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    /**
     * Logs in an existing user
     */
    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Enter an E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        pdLoad.setMessage("Logging in...");
        pdLoad.show();
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pdLoad.dismiss();
                        if(task.isSuccessful()) {
                            finish();
                            Intent login = new Intent(getApplicationContext(), ProfileActivity.class);
                            LoginActivity.this.startActivity(login);
                        } else {
                            final TextView tvIncorrect = (TextView) findViewById(R.id.tvIncorrect);
                            tvIncorrect.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }
}
