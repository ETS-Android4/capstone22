package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.HashMap;

public class RegisterPageActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mConfirmPassword,mPhone,mCardNum;
    Button mLoginButton;
    Button mRegisterButton;
    //private FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //------------------------
        // Comes first to get rid of the white default loading screen
        // Eseentially this is wokring to bypass and remove default white screen that pops up when loading app.
        // May change this later. ALSO This is also linking to Themes.xml's under Res/Values/themes.
        setTheme(R.style.Theme_Carolina_Coffee);
        //------------------------

        // Get rid of the top "Carolina_Coffee" purple bar on top of each page.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getSupportActionBar().hide(); // This line will hide the action bar

        // This will change the action bar color from the default purple, to color of choice here.
        // Calling to method that will make this action happen.
        statusBarColor();

        setContentView(R.layout.activity_register);

        mFullName       = findViewById(R.id.fullName);
        mEmail          = findViewById(R.id.email);
        mPassword       = findViewById(R.id.password);
        mConfirmPassword= findViewById(R.id.confirmPassword);
        mRegisterButton = findViewById(R.id.registerButton);
        mLoginButton    = findViewById(R.id.loginButton);
        mPhone          = findViewById(R.id.phone);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        // ** AUTO FORMAT Phone Number
        //-----------------------------------------------------------
        final EditText editText = (EditText) findViewById(R.id.phone);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void afterTextChanged(Editable s) {
                String text = editText.getText().toString();
                int  textLength = editText.getText().length();
                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return;
                if (textLength == 1) {
                    if (!text.contains("(")) {
                        editText.setText(new StringBuilder(text).insert(text.length() - 1, "(").toString());
                        editText.setSelection(editText.getText().length());
                    }
                } else if (textLength == 5) {
                    if (!text.contains(")")) {
                        editText.setText(new StringBuilder(text).insert(text.length() - 1, ")").toString());
                        editText.setSelection(editText.getText().length());
                    }
                }
                else if (textLength == 6) {
                    editText.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    editText.setSelection(editText.getText().length());
                }
                else if (textLength == 10) {
                    if (!text.contains("-"))
                    {
                        editText.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editText.setSelection(editText.getText().length());
                    }
                }
                else if (textLength == 15) {
                    if (text.contains("-")) {
                        editText.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editText.setSelection(editText.getText().length());
                    }
                }
                else if (textLength == 18) {
                    if (text.contains("-")) {
                        editText.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editText.setSelection(editText.getText().length());
                    }
                }
            }
        });
        // End of auto format phone number
        //-----------------------------------------------------------


        // If user is already logged in, take user to main page.
        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        // On click method for Register Button.
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                        String fullName = mFullName.getText().toString().trim();
                        String email = mEmail.getText().toString().trim();
                        String password = mPassword.getText().toString().trim();
                        String confirmPassword = mConfirmPassword.getText().toString().trim();
                        String phone = mPhone.getText().toString();
                        //tring cardNum = mCardNum.getText().toString();





                        // If user does not enter a name.
                        if(TextUtils.isEmpty(fullName)) {
                            Toast.makeText(RegisterPageActivity.this, "Your name is required.", Toast.LENGTH_SHORT).show();
                            mFullName.setError("Name is required.");
                            return;
                        }

                        // If user does not enter an email in the field
                        if(TextUtils.isEmpty(email)) {
                            Toast.makeText(RegisterPageActivity.this, "Email is required.", Toast.LENGTH_SHORT).show();
                            mEmail.setError("Email is required.");
                            return;
                        }

                        // If user does not enter a password in the field
                        if(TextUtils.isEmpty(password)) {
                            Toast.makeText(RegisterPageActivity.this, "Password is required.", Toast.LENGTH_SHORT).show();
                            mPassword.setError("Password is required.");
                            return;
                        }

                        // If user enters a password <= 8 characters.
                        if(password.length() < 8) {
                            Toast.makeText(RegisterPageActivity.this, "Password must be >= 8 characters.", Toast.LENGTH_SHORT).show();
                            mPassword.setError("Password must be >= 8 characters.");
                            return;
                        }

                        // If user passwords do not match.
                        if(!password.equals(confirmPassword)) {
                            Toast.makeText(RegisterPageActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
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




                                    //Send confirmation email / verification link.
                                    FirebaseUser fuser = fAuth.getCurrentUser();
                                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterPageActivity.this, "Verification Email Has Been Sent!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure: Email not sent.\n" + e.getMessage());
                                        }
                                    });





                                    Toast.makeText(RegisterPageActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                                    userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("fName",fullName);
                                    user.put("email",email);
                                    user.put("phone",phone);
                                    //user.put("Card_Number",cardNum);



                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure: " + e.toString());
                                        }
                                    });

                                    //DECLARING Users rewards status to zero on firebase.
                                    declareUsersRewardStatusToZero();

                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                }else {
                                    Toast.makeText(RegisterPageActivity.this, "Error has occurred!\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(getApplicationContext(), LoginPageActivity.class));
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

    //Declaring users rewards increment to zero upon registration.
    private void declareUsersRewardStatusToZero() {

        FirebaseUser fuser = fAuth.getCurrentUser();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("rewards_increment").document(userID);

        int increment = 0;
        Map<String,Integer> user = new HashMap<>();
        user.put("Increment", increment );

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.toString());
            }
        });

    }


}