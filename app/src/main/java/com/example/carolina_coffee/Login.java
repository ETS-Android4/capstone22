package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginButton, mCreateButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginButton = findViewById(R.id.loginButton);
        mCreateButton = findViewById(R.id.createButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();



                // If user does not enter an email in the field
                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required.");
                    return;
                }

                // If user does not enter a password in the field
                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }

                // If user enters a password <= 8 characters.
                if(password.length() < 8) {
                    mPassword.setError("Password must be >= 8 characters.");
                    return;
                }

                // Sets progress bar to VISIBLE to show the user a background process is occurring.
                progressBar.setVisibility(View.VISIBLE);

                // Authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Error has occurred!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            // Hide loading bar when user gets an error.
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }

    // Create Account Button -> Send user to Register Page.
    public void createAccountButton(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }


}