package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class EditPaymentPlansActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    EditText mBilling_Name_1, mCardNumber_1, mExp_Date_1, mCCV_Num_1, mZip_1;
    Button mAdd_Payment_1,mDelete_payment_1,mDelete_payment_2;
    Button mPayment_method_box_1, mPayment_method_box_2;
    TextView mCard_Ending_1, mCard_Ending_2;
    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    private View view;

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
        setContentView(R.layout.activity_edit_payment_plans);

        // Delete User DATA for Payment 1 & 2
        //-------------------------------------------
        mDelete_payment_1  = findViewById(R.id.delete_payment_1);
        mDelete_payment_2  = findViewById(R.id.delete_payment_2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();


        // ** METHOD TO DELETE 1
        mDelete_payment_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deletePaymentMethod_1(view);
                StringBuilder number = new StringBuilder("1");
                deletePayments(number);
            }
        });

        // ** METHOD TO DELETE 2
        mDelete_payment_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deletePaymentMethod_2(view);
                StringBuilder number = new StringBuilder("2");
                deletePayments(number);
            }
        });
        // **END** OF Delete User DATA for Payment 1.
        //-------------------------------------------

        //Display last 4 digits on card if user added one.
        displayDigits_1();
        displayDigits_2();













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
                        startActivity(new Intent(getApplicationContext(), RewardsPageActivity.class));
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

    public void settingsPage(View view) {
        Intent intent = new Intent(this, SettingPageActivity.class);
        startActivity(intent);
    }

    public void payment_method_1(View view) {
        Intent intent = new Intent(this, PaymentMethod_1_Activity.class);
        overridePendingTransition(5,5);
        startActivity(intent);
    }

    public void payment_method_2(View view) {
        Intent intent = new Intent(this, PaymentMethod_2_Activity.class);
        overridePendingTransition(5,5);
        startActivity(intent);
    }

    //Enable / Disable edit and done Buttons to make edits / deletes to payment methods.
    // Changing VISIBILITY buttons
    public void edit_payment_button(View view) {
        // DONE button Appears
        View doneButton = findViewById(R.id.edit_cards_button_done);
        doneButton.setVisibility(View.VISIBLE);
        // EDIT button Disappears
        View editButton = findViewById(R.id.edit_cards_button);
        editButton.setVisibility(View.INVISIBLE);

        // DELETE BUTTONS APPEAR
        // PaymentMethod 1
        View delete_payment_method_1 = findViewById(R.id.delete_payment_1);
        delete_payment_method_1.setVisibility(View.VISIBLE);
        // PaymentMethod 2
        View delete_payment_method_2 = findViewById(R.id.delete_payment_2);
        delete_payment_method_2.setVisibility(View.VISIBLE);

        //DISABLE payment_method_box Buttons
        View payment_Button_1 = findViewById(R.id.payment_method_box_1);
        View payment_Button_2 = findViewById(R.id.payment_method_box_2);
        payment_Button_1.setEnabled(false);
        payment_Button_2.setEnabled(false);


    }


    //Disable PaymentMethod 1 button from PaymentMethod_1_Activity
    public void disablePaymentMethod_1_Button() {
        View mPayment_method_box_1 = findViewById(R.id.payment_method_box_1);
        mPayment_method_box_1.setEnabled(false);
        View mPayment_method_arrow_1 = findViewById(R.id.card_arrow_1);
        mPayment_method_arrow_1.setEnabled(false);
        mPayment_method_arrow_1.setVisibility(View.INVISIBLE);

    }
    //ENABLE PaymentMethod 1 button from PaymentMethod_1_Activity
    public void enablePaymentMethod_1_Button() {
        View mPayment_method_box_1 = findViewById(R.id.payment_method_box_1);
        mPayment_method_box_1.setEnabled(true);
        View mPayment_method_arrow_1 = findViewById(R.id.card_arrow_1);
        mPayment_method_arrow_1.setEnabled(true);
        mPayment_method_arrow_1.setVisibility(View.VISIBLE);
    }

    //Disable PaymentMethod 2 button from PaymentMethod_2_Activity
    public void disablePaymentMethod_2_Button() {
        View mPayment_method_box_2 = findViewById(R.id.payment_method_box_2);
        mPayment_method_box_2.setEnabled(false);
        View mPayment_method_arrow_2 = findViewById(R.id.card_arrow_2);
        mPayment_method_arrow_2.setEnabled(false);
        mPayment_method_arrow_2.setVisibility(View.INVISIBLE);

    }
    //ENABLE PaymentMethod 2 button from PaymentMethod_2_Activity
    public void enablePaymentMethod_2_Button() {
        View mPayment_method_box_2 = findViewById(R.id.payment_method_box_2);
        mPayment_method_box_2.setEnabled(true);
        View mPayment_method_arrow_2 = findViewById(R.id.card_arrow_2);
        mPayment_method_arrow_2.setEnabled(true);
        mPayment_method_arrow_2.setVisibility(View.VISIBLE);
    }

    //DeleteAnyPaymentMethod
    public void deletePayments(StringBuilder number) {
        //Send confirmation email / verification link.
        FirebaseUser fuser = fAuth.getCurrentUser();

        Toast.makeText(EditPaymentPlansActivity.this, "Payment Method "+number+" Deleted.", Toast.LENGTH_SHORT).show();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("PaymentMethod_"+number).document(userID);
        Map<String,Object> user = new HashMap<>();

        user.remove("Billing_Name_"+number);
        user.remove("Billing_Card_Num_"+number);
        user.remove("Billing_Exp_Date_"+number);
        user.remove("Billing_CCV_Num_"+number);
        user.remove("Billing_Zip_"+number);
        edit_payment_button_done(view);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: "+ userID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.toString());
            }
        });
        //Start new activity
    }


    public void edit_payment_button_done(View view) {
        // DONE button Disappears
        View doneButton = findViewById(R.id.edit_cards_button_done);
        doneButton.setVisibility(View.INVISIBLE);
        // EDIT button Appears
        View editButton = findViewById(R.id.edit_cards_button);
        editButton.setVisibility(View.VISIBLE);

        // DELETE BUTTONS DISAPPEAR
        // PaymentMethod 1
        View delete_payment_method_1 = findViewById(R.id.delete_payment_1);
        delete_payment_method_1.setVisibility(View.INVISIBLE);
        // PaymentMethod 2
        View delete_payment_method_2 = findViewById(R.id.delete_payment_2);
        delete_payment_method_2.setVisibility(View.INVISIBLE);

        //ENABLE payment_method_box Buttons
        View payment_Button_1 = findViewById(R.id.payment_method_box_1);
        View payment_Button_2 = findViewById(R.id.payment_method_box_2);
        payment_Button_1.setEnabled(true);
        payment_Button_2.setEnabled(true);
    }



    // DISPLAY Last 4 digits for payment method 1
    public void displayDigits_1() {
        //Display last 4 digits if user has card
        mCard_Ending_1 = findViewById(R.id.card_ending_1);
        // Displaying Card info FROM FireBase
        DocumentReference documentReference = fStore.collection("PaymentMethod_1").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            // Card 1
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    String card_1 = documentSnapshot.getString("Billing_Card_Num_1");
                    String lastFourDigits_1 = "";
                    if(documentSnapshot.getString("Billing_Card_Num_1") == null) {
                        mCard_Ending_1.setText("No Card.");
                        enablePaymentMethod_1_Button();

                        return;
                    } else {
                        if (card_1.length() > 4) {
                            lastFourDigits_1 = card_1.substring(card_1.length() - 4);
                        } else {
                            lastFourDigits_1 = card_1;
                        }
                    }
                    mCard_Ending_1.setText("Card Ending in: " + lastFourDigits_1);
                    disablePaymentMethod_1_Button();
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

    }
    // DISPLAY Last 4 digits for payment method 2
    public void displayDigits_2() {
        //Display last 4 digits if user has card
        mCard_Ending_2 = findViewById(R.id.card_ending_2);
        // Displaying Card info FROM FireBase
        DocumentReference documentReference = fStore.collection("PaymentMethod_2").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            // Card 2
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    String card_2 = documentSnapshot.getString("Billing_Card_Num_2");
                    String lastFourDigits_2 = "";
                    if(documentSnapshot.getString("Billing_Card_Num_2") == null) {
                        mCard_Ending_2.setText("No Card.");
                        enablePaymentMethod_2_Button();
                        return;
                    } else {
                        if (card_2.length() > 4) {
                            lastFourDigits_2 = card_2.substring(card_2.length() - 4);
                        } else {
                            lastFourDigits_2 = card_2;
                        }
                    }
                    mCard_Ending_2.setText("Card Ending in: " + lastFourDigits_2);
                    disablePaymentMethod_2_Button();
                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }

        });
    }

}