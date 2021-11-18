package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
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

        // Get rid of the top "Carolina_Coffee" purple bar on top of each page.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getSupportActionBar().hide(); // This line will hide the action bar
        // This will change the action bar color from the default purple, to color of choice here.
        // Calling to method that will make this action happen.
        statusBarColor();
        setContentView(R.layout.activity_main);



        // Navigation
        //--------------------------------------------------------------------------------------
        //Initialize and assign variables - bottom_navigation = nav bar inside activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.homePageButton);
        //perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //Home Page Button
                    case R.id.homePageButton:
                        return true;

                    //Payment Page Button
                    case R.id.payPageButton:
                        startActivity(new Intent(getApplicationContext(), PaymentActivity13.class));
                        overridePendingTransition(0,0);
                        return true;

                    //Order Page Button
                    case R.id.orderPageButton:
                        startActivity(new Intent(getApplicationContext(),RewardsActivity2.class));
                        overridePendingTransition(0,0);
                        return true;

                    //Account Page Button
                    case R.id.accountPageButton:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        // End of Navigation
        //--------------------------------------------------------------------------------------





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

    // This is method to change the status bar color from default purple to color of choice.
    private void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }

    public void rewardsActivityButton(View view) {
        Intent intent = new Intent(this, RewardsActivity2.class);
        startActivity(intent);
    }

    public void paymentActivity13(View view) {
        Intent intent = new Intent(this, PaymentActivity13.class);
        startActivity(intent);
    }

}