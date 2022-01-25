package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText profileName, profileEmail, profilePhone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    TextView confirmChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //------------------------
        // Comes first to get rid of the white default loading screen
        // Eseentially this is wokring to bypass and remove default white screen that pops up when loading app.
        // May change this later. ALSO This is also linking to Themes.xml's under Res/Values/themes.
        setTheme(R.style.Theme_Carolina_Coffee);
        //------------------------

        // Remove default top bar
        // --------------------------------------------------------------------
        // Get rid of the top "Carolina_Coffee" purple bar on top of each page.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getSupportActionBar().hide(); // This line will hide the action bar
        // This will change the action bar color from the default purple, to color of choice here.
        // Calling to method that will make this action happen.
        statusBarColor();
        // End of default top bar removal.
        // --------------------------------------------------------------------

        setContentView(R.layout.activity_edit_profile);

        // Edit users full name, email, and phone
        // --------------------------------------------------------------------
        //Pull variables from Profile Page to display in text field for editing information.
        Intent data = getIntent();
        String fullName = data.getStringExtra("fullName");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        // Establish Variables
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
        confirmChangesButton = findViewById(R.id.confirmChangesButton);
        //Pull data from fireBase via previous page, and insert text into box.
        profileEmail.setText(email);
        profileName.setText(fullName);
        profilePhone.setText(phone);

        //Log.d(TAG,"onCreate: " + fullName + " " + email + " " + phone );

        // ** AUTO FORMAT Phone Number
        //-----------------------------------------------------------
        final EditText editText = (EditText) findViewById(R.id.profilePhone);
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

        // Grab new data changes and update to FireBase
        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If any new data is left blank/empty, push an error to user.
                if(profileName.getText().toString().isEmpty() || profileEmail.getText().toString().isEmpty() || profilePhone.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "You cannot leave any fields blank.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Extract email
                String email = profileEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("fName",profileName.getText().toString());
                        edited.put("phone",profilePhone.getText().toString());

                        //Can add on success/failure listeners here to see backgroun errors
                        //docRef.update(edited).OnSuccess
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditProfileActivity.this, "Profile Updated.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ProfilePageActivity.class));
                                finish();
                            }
                        });
                        Toast.makeText(EditProfileActivity.this, "Email has been changed.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileActivity.this, "Error!\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // End of editing users name, email, phone
        // --------------------------------------------------------------------


        // Navigation
        //--------------------------------------------------------------------------------------
        //Initialize and assign variables - bottom_navigation = nav bar inside activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.accountPageButton);
        //perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //Home Page Button
                    case R.id.homePageButton:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //Payment Page Button
                    case R.id.payPageButton:
                        startActivity(new Intent(getApplicationContext(), CartPageActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //Order Page Button
                    case R.id.orderPageButton:
                        startActivity(new Intent(getApplicationContext(), MenuPageActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //Account Page Button
                    case R.id.accountPageButton:
                        startActivity(new Intent(getApplicationContext(), SettingPageActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        // End of Navigation
        //--------------------------------------------------------------------------------------
    }


    // This is method to change the status bar color from default purple to color of choice.
    private void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }


    //Cancel Button and Back Button
    // Send user back to profile page.
    public void profilePage(View view) {
        Intent intent = new Intent(this, ProfilePageActivity.class);
        startActivity(intent);
    }
}