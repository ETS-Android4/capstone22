package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Button increase, decrease;
    int newRewardsNum=0;

    // Rewards Increment - save data
    private ProgressBar progressBar;
    private TextView point_bar_red_p1;
    private TextView point_bar_red_p2;
    private TextView point_bar_red_p3;
    private TextView point_bar_red_p4;
    private TextView circle_coffee_MAX;
    Button redeem_rewards_btn;
    public static final String SHARED_PREF = "Myscore";
    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String TAG = "TAG";

    //Dialog box for payment method
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String result = "";


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



        //for rewards - Modified by Chris
        //setContentView(R.layout.activity_main);
        // --------------------------------------------------------------------------------------------
        point_bar_red_p1 = findViewById(R.id.point_bar_red_p1);
        point_bar_red_p2 = findViewById(R.id.point_bar_red_p2);
        point_bar_red_p3 = findViewById(R.id.point_bar_red_p3);
        point_bar_red_p4 = findViewById(R.id.point_bar_red_p4);
        circle_coffee_MAX = findViewById(R.id.circle_coffee_MAX);
        redeem_rewards_btn = findViewById(R.id.redeem_rewards_btn);
        t1=findViewById(R.id.earned_points);
        progressBar = findViewById(R.id.default_gray_circle);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, 0);
        newRewardsNum = sharedPreferences.getInt(SHARED_PREF + userID, 0);
        t1.setText(Integer.toString(newRewardsNum));
        rewardsNum = (TextView) findViewById(R.id.earned_points);

        // Increase Button tester / code.
        increase = (Button) findViewById(R.id.testRewardsButton);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRewardsNum++;
                rewardsNum.setText(String.valueOf(newRewardsNum));
                setBars();

                saveRewardData();
            }
        });

        // Decrease Button Tester / code
        decrease = (Button) findViewById(R.id.testRewardsButton2);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // only allows decrements if greater than or equal to 4.
                if(newRewardsNum >= 4) {
                    newRewardsNum --;
                    newRewardsNum --;
                    newRewardsNum --;
                    newRewardsNum --;
                } else {
                    return;
                }
                rewardsNum.setText(String.valueOf(newRewardsNum));
                setBars();
                saveRewardData();
            }
        });

        // Redeem Rewards Button
        circle_coffee_MAX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating pop-up dialog box..
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("You have a $5 Coupon!");
                builder.setMessage("In order to redeem your $5 coupon, you must have a minimum purchase of $6.");

                // YES button - User clicked YES
                builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MenuPageActivity.class));
                        overridePendingTransition(0,0);
                    }
                });

                // CANCEL button - user clicked CANCEL
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = "";
                    }
                });
                dialog = builder.create();
                dialog.show();
                // -----------------------------------------------------
            }
        });
        // Needed to update rewards.
        updateRewards();

        // End of increment Rewards.
        // --------------------------------------------------------------------------------------------




    }
    // Working function for Rewards Increments
    //----------------------------------------------------------------------------------------------

    private void setBars() {
        if(newRewardsNum == 0) {
            //point_bar_red_p1.setVisibility(View.INVISIBLE);
            //point_bar_red_p2.setVisibility(View.INVISIBLE);
            //point_bar_red_p3.setVisibility(View.INVISIBLE);
            //point_bar_red_p4.setVisibility(View.INVISIBLE);
            circle_coffee_MAX.setVisibility(View.INVISIBLE);
            progressBar.setProgress(0);
            // Increment.
            int increment = 0;
            incrementRewards(increment);
        } else if(newRewardsNum == 1) {
            //point_bar_red_p1.setVisibility(View.VISIBLE);
            //point_bar_red_p2.setVisibility(View.INVISIBLE);
            //point_bar_red_p3.setVisibility(View.INVISIBLE);
            //point_bar_red_p4.setVisibility(View.INVISIBLE);
            circle_coffee_MAX.setVisibility(View.INVISIBLE);
            progressBar.setProgress(12);
            // Increment.
            int increment = 1;
            incrementRewards(increment);
        } else if(newRewardsNum == 2) {
            //point_bar_red_p1.setVisibility(View.VISIBLE);
            //point_bar_red_p2.setVisibility(View.VISIBLE);
            //point_bar_red_p3.setVisibility(View.INVISIBLE);
            //point_bar_red_p4.setVisibility(View.INVISIBLE);
            circle_coffee_MAX.setVisibility(View.INVISIBLE);
            progressBar.setProgress(25);
            // Increment.
            int increment = 2;
            incrementRewards(increment);
        } else if(newRewardsNum == 3) {
            //point_bar_red_p1.setVisibility(View.VISIBLE);
            //point_bar_red_p2.setVisibility(View.VISIBLE);
            //point_bar_red_p3.setVisibility(View.VISIBLE);
            //point_bar_red_p4.setVisibility(View.INVISIBLE);
            circle_coffee_MAX.setVisibility(View.INVISIBLE);
            progressBar.setProgress(37);
            // Increment.
            int increment = 3;
            incrementRewards(increment);
        } else if(newRewardsNum == 4) {
            //point_bar_red_p1.setVisibility(View.VISIBLE);
            //point_bar_red_p2.setVisibility(View.VISIBLE);
            //point_bar_red_p3.setVisibility(View.VISIBLE);
            //point_bar_red_p4.setVisibility(View.VISIBLE);
            circle_coffee_MAX.setVisibility(View.VISIBLE);
            progressBar.setProgress(100);
            // Increment.
            int increment = 4;
            incrementRewards(increment);
        } else if(newRewardsNum < 0) {
            // Something went wrong if this happens
            //point_bar_red_p1.setVisibility(View.INVISIBLE);
            //point_bar_red_p2.setVisibility(View.INVISIBLE);
            //point_bar_red_p3.setVisibility(View.INVISIBLE);
            //point_bar_red_p4.setVisibility(View.INVISIBLE);
            circle_coffee_MAX.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            // Increment.
            int increment = 0;
            incrementRewards(increment);
        } else {
            //point_bar_red_p1.setVisibility(View.VISIBLE);
            //point_bar_red_p2.setVisibility(View.VISIBLE);
            //point_bar_red_p3.setVisibility(View.VISIBLE);
            //point_bar_red_p4.setVisibility(View.VISIBLE);
            circle_coffee_MAX.setVisibility(View.VISIBLE);
            progressBar.setProgress(100);
            // Increment.
            //int increment = 5;
            int increment = newRewardsNum;
            incrementRewards(increment);
        }
        //Display / Update Redeem button
        secret_Menu_FireBase();
    }

    // Add increment to firebase.
    private void incrementRewards(int increment) {
        FirebaseUser fuser = fAuth.getCurrentUser();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("rewards_increment").document(userID);
        Map<String,Object> user = new HashMap<>();
        user.put("Increment", increment );
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: user Profile is created for ::: "+ userID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e);
            }
        });
    }

    private void saveRewardData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(SHARED_PREF + userID, newRewardsNum);
        editor.apply();
        Toast.makeText(this, "Rewards Saved", Toast.LENGTH_SHORT).show();
    }
    public void updateRewards() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        newRewardsNum = sharedPreferences.getInt(SHARED_PREF + userID, 0);

        if (newRewardsNum > 6) {
            newRewardsNum = 0;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREF + userID, newRewardsNum);
        editor.apply();

        rewardsNum.setText(String.valueOf(newRewardsNum));
        setBars();
    }

    public void secret_Menu_FireBase() {
        DocumentReference documentReference = fStore.collection("rewards_increment").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    // Checks firebase and which value is located in increment to update buttons.
                    if(documentSnapshot.getLong("Increment") == 0) {
                        circle_coffee_MAX.setVisibility(View.INVISIBLE);
                        return;
                    } else if(documentSnapshot.getLong("Increment") == 1) {
                        circle_coffee_MAX.setVisibility(View.INVISIBLE);
                        return;
                    } else if(documentSnapshot.getLong("Increment") == 2) {
                        circle_coffee_MAX.setVisibility(View.INVISIBLE);
                        return;
                    } else if(documentSnapshot.getLong("Increment") == 3) {
                        circle_coffee_MAX.setVisibility(View.INVISIBLE);
                        return;
                    } else if(documentSnapshot.getLong("Increment") == 4) {
                        circle_coffee_MAX.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        circle_coffee_MAX.setVisibility(View.VISIBLE);
                        return;
                    }
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
    }

    // END OF Working function for Rewards Increments
    //----------------------------------------------------------------------------------------------



    // Status Bar Color
    public void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }

    public void historyPageButton(View view) {
        //Takes user to duplicate history page, except this dupe page allows back buttons to send user to home screen instead.
        Intent intent = new Intent(this, HistoryPageActivity2.class);
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