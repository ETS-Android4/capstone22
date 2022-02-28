package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    int score=0;
    TextView fullName,email,phone;
    TextView caccount_box, caccount_settings, caccount_settings2;
    Button cact_btn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;
    StorageReference storageReference;

    private ImageView loadingImage;

    private Button loginButton;
    TextView rewardsNum;
    Button increase;
    int newRewardsNum=0;


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


        // Display user verified box on main page
        // -----------------------------------------------------
        // Verify account creation
        caccount_box = findViewById(R.id.account_box);
        caccount_settings = findViewById(R.id.account_settings);
        caccount_settings2 = findViewById(R.id.account_settings2);
        cact_btn = findViewById(R.id.act_btn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        // user VERIFIED?

        if(!user.isEmailVerified()) {
            caccount_box.setVisibility(View.VISIBLE);
            caccount_settings.setVisibility(View.VISIBLE);
            caccount_settings2.setVisibility(View.VISIBLE);
            cact_btn.setVisibility(View.VISIBLE);
        }




        // -----------------------------------------------------





/*
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity2();
            }
        });

 */

        //for rewards
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.earned_points);

        SharedPreferences sp = this.getSharedPreferences("Myscore", Context.MODE_PRIVATE);
        newRewardsNum = sp.getInt("score", 0);
        t1.setText(Integer.toString(newRewardsNum));

        rewardsNum = (TextView) findViewById(R.id.earned_points);
        increase = (Button) findViewById(R.id.testRewardsButton);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRewardsNum++;
                rewardsNum.setText(String.valueOf(newRewardsNum));
            }
        });


    }

    // Status Bar Color
    public void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }

    /*
    //Fire Base Sign Out Method
    public void logout(View view) {
        // Logout
        FirebaseAuth.getInstance().signOut();
        // Send user to login page
        startActivity(new Intent(getApplicationContext(), LoginPageAtivity.class));
        overridePendingTransition(0,0);
        finish();
    }
     */

    public void historyPageButton(View view) {
        Intent intent = new Intent(this, HistoryPageActivity.class);
        startActivity(intent);
    }
    public void inboxPage(View view) {
        Intent intent = new Intent(this, InboxPageActivity.class);
        startActivity(intent);
    }
    public void account_page(View view) {
        Intent intent = new Intent(this, ProfilePageActivity.class);
        startActivity(intent);
    }
    public boolean order_coffee(View view) {
        startActivity(new Intent(getApplicationContext(), MenuPageActivity.class));
        overridePendingTransition(0,0);
        return true;
    }


}