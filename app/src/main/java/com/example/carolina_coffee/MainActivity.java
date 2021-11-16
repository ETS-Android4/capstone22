package com.example.carolina_coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    TextView fullName,email,phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity2();
            }
        });
    }

    //Fire Base Sign Out Method
    public void logout(View view) {
        // Logout
        FirebaseAuth.getInstance().signOut();
        // Send user to login page
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


    public void openLoginActivity2() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void profilePage(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}