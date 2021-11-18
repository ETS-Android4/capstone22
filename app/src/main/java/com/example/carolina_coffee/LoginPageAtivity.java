package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageAtivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mCreateButton, mForgotPassword;
    ImageView mLoginButton;
    //TextView mForgotPassword;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get rid of the top "Carolina_Coffee" purple bar on top of each page.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getSupportActionBar().hide(); // This line will hide the action bar

        // This will change the action bar color from the default purple, to color of choice here.
        // Calling to method that will make this action happen.
        statusBarColor();

        setContentView(R.layout.activity_login);

        mEmail          = findViewById(R.id.email);
        mPassword       = findViewById(R.id.password);
        progressBar     = findViewById(R.id.progressBar);
        fAuth           = FirebaseAuth.getInstance();
        mLoginButton    = findViewById(R.id.loginButton);
        mCreateButton   = findViewById(R.id.createButton);
        mForgotPassword = findViewById(R.id.forgotPassword);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();



                // If user does not enter an email in the field
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginPageAtivity.this, "Email is required.", Toast.LENGTH_SHORT).show();
                    mEmail.setError("Email is required.");
                    return;
                }

                // If user does not enter a password in the field
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginPageAtivity.this, "Password is required.", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(LoginPageAtivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(LoginPageAtivity.this, "Error has occurred!\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            // Hide loading bar when user gets an error.
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter your Email to receive password reset link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Extract the email and send reset link.

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginPageAtivity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginPageAtivity.this, "Error! Reset Link Was Not Sent.\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the dialog box if user clicks no.


                    }
                });
                // Display the dialog box for reset Password.
                passwordResetDialog.create().show();
            }
        });

    }

    // Create Account Button -> Send user to Register Page.
    public void createAccountButton(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterPageActivity.class));
        finish();
    }

    // This is method to change the status bar color from default purple to color of choice.
    private void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }


}