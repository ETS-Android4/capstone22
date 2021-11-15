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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mConfirmPassword,mPhone;
    Button mLoginButton;
    ImageView mRegisterButton;
    private FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName       = findViewById(R.id.fullName);
        mEmail          = findViewById(R.id.email);
        mPassword       = findViewById(R.id.password);
        mConfirmPassword= findViewById(R.id.confirmPassword);
        mRegisterButton = findViewById(R.id.registerButton);
        mLoginButton    = findViewById(R.id.loginButton);
        mPhone          = findViewById(R.id.phone);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        // If user is already logged in, take user to main page.
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        // On click method for Register Button.
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                        String name = mFullName.getText().toString().trim();
                        String email = mEmail.getText().toString().trim();
                        String password = mPassword.getText().toString().trim();
                        String confirmPassword = mConfirmPassword.getText().toString().trim();



                        // If user does not enter a name.
                        if(TextUtils.isEmpty(name)) {
                            Toast.makeText(Register.this, "Your name is required.", Toast.LENGTH_SHORT).show();
                            mFullName.setError("Name is required.");
                            return;
                        }

                        // If user does not enter an email in the field
                        if(TextUtils.isEmpty(email)) {
                            Toast.makeText(Register.this, "Email is required.", Toast.LENGTH_SHORT).show();
                            mEmail.setError("Email is required.");
                            return;
                        }

                        // If user does not enter a password in the field
                        if(TextUtils.isEmpty(password)) {
                            Toast.makeText(Register.this, "Password is required.", Toast.LENGTH_SHORT).show();
                            mPassword.setError("Password is required.");
                            return;
                        }

                        // If user enters a password <= 8 characters.
                        if(password.length() < 8) {
                            Toast.makeText(Register.this, "Password must be >= 8 characters.", Toast.LENGTH_SHORT).show();
                            mPassword.setError("Password must be >= 8 characters.");
                            return;
                        }

                        // If user passwords do not match.
                        if(!password.equals(confirmPassword)) {
                            Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                            mPassword.setError("Passwords must be identical!");
                            mConfirmPassword.setError("Passwords must be identical!");
                            return;
                        }

                        // Sets progress bar to VISIBLE to show the user a background process is occurring.
                        progressBar.setVisibility(View.VISIBLE);


                        // Register the user in firebase.
                        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                }else {
                                    Toast.makeText(Register.this, "Error has occurred!\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    // Hide loading bar when user gets an error.
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

            }
        });



    }

    // Button for existing account -> send user to login page.
    public void existingAccountLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


}