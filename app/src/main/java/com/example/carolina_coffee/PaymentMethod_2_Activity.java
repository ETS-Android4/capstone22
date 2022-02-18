package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethod_2_Activity extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText mBilling_Name_2, mCardNumber_2, mExp_Date_2, mCCV_Num_2, mZip_2;
    TextView dFullName, dCardNum, dExp_Date;
    Button mAdd_Payment_2;
    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;

    EditPaymentPlansActivity editPaymentPlansActivity;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    private Object Editable;

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
        setContentView(R.layout.activity_payment_method2);

        mAdd_Payment_2     = findViewById(R.id.add_payment_method_button_2);
        mBilling_Name_2    = findViewById(R.id.fullName_box_2);
        mCardNumber_2      = findViewById(R.id.card_number_box_2);
        mExp_Date_2        = findViewById(R.id.exp_date_box_2);
        mCCV_Num_2         = findViewById(R.id.ccv_num_box_2);
        mZip_2             = findViewById(R.id.billing_zip_box_2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();


        // *** Collecting User Payment Method 2 ***
        //-----------------------------------------------
        mAdd_Payment_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName_String = mBilling_Name_2.getText().toString().trim();
                String cardNumber_String = mCardNumber_2.getText().toString().trim();
                String exp_Date_String = mExp_Date_2.getText().toString().trim();
                String CCV_Num_String = mCCV_Num_2.getText().toString().trim();
                String zip_Code_String = mZip_2.getText().toString().trim();

                // If user does not enter a name.
                if(TextUtils.isEmpty(fullName_String)) {
                    Toast.makeText(PaymentMethod_2_Activity.this, "Your name is required.", Toast.LENGTH_SHORT).show();
                    mBilling_Name_2.setError("Name is required.");
                    return;
                }
                // If user does not enter a Card or card length < 19
                if(TextUtils.isEmpty(cardNumber_String) || (cardNumber_String.length()<19) ) {
                    Toast.makeText(PaymentMethod_2_Activity.this, "Your Card is required.", Toast.LENGTH_SHORT).show();
                    mCardNumber_2.setError("Card is required.");
                    return;
                }
                // If user does not enter a exp Date or length < 4.
                if(TextUtils.isEmpty(exp_Date_String) || exp_Date_String.length()<4) {
                    Toast.makeText(PaymentMethod_2_Activity.this, "Your Exp Date is required.", Toast.LENGTH_SHORT).show();
                    mExp_Date_2.setError("Exp Date is required.");
                    return;
                }
                // If user does not enter a CCV or length < 3.
                if(TextUtils.isEmpty(CCV_Num_String) || (CCV_Num_String.length()<3) ) {
                    Toast.makeText(PaymentMethod_2_Activity.this, "Your CCV is required.", Toast.LENGTH_SHORT).show();
                    mCCV_Num_2.setError("CCV is required.");
                    return;
                }
                // If user does not enter a Zip or length < 5
                if(TextUtils.isEmpty(zip_Code_String) || (zip_Code_String.length()<5) ) {
                    Toast.makeText(PaymentMethod_2_Activity.this, "Your Zip Code is required.", Toast.LENGTH_SHORT).show();
                    mZip_2.setError("Zip Code is required.");
                    return;
                }

                // if exp date is invalid format:
                String[] string = exp_Date_String.replaceAll("\\[", "").replaceAll("]","").split("/");
                int[] arr = new int[string.length];

                arr[0] = Integer.parseInt(string[0]);
                arr[1] = Integer.parseInt(string[1]);
                //If month is > 12
                if(arr[0] > 12) {
                    //invalid date
                    Toast.makeText(PaymentMethod_2_Activity.this, "invalid date.", Toast.LENGTH_SHORT).show();
                    mExp_Date_2.setError("invalid date");
                    return;
                }
                //if year is < 22 or greater than 30
                if(arr[1] < 22 || arr[1] > 30) {
                    //invalid date
                    Toast.makeText(PaymentMethod_2_Activity.this, "invalid date.", Toast.LENGTH_SHORT).show();
                    mExp_Date_2.setError("invalid date");
                    return;
                }

                else {
                    addDataToFireBase(fullName_String, cardNumber_String, exp_Date_String, CCV_Num_String,zip_Code_String);
                    // Start new Page
                    startActivity(new Intent(getApplicationContext(), EditPaymentPlansActivity.class));
                    overridePendingTransition(2,5);
                }


            }
        });

        // FORMAT LAYOUT FOR TEXT EDITS
        //-------------------------------------------------------------------------------
        // CREDIT CARD Formatting
        TextView card_number_format = (TextView) findViewById(R.id.card_number_box_2);
        card_number_format.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(android.text.Editable s) {
                String initial = s.toString();
                // remove all non-digits characters.
                String processed = initial.replaceAll("\\D", "");
                // insert a space after all groups of 4 digits that are followed by another digit.
                processed = processed.replaceAll("(\\d{4})(?=\\d)", "$1-");
                // to avoid stackoverflow errors, check that the processed is different from what's already
                //  there before setting
                if (!initial.equals(processed)) {
                    // set the value
                    s.replace(0, initial.length(), processed);
                }
            }

        });
        // EXPIRATION DATE Formatting
        EditText exp_date_box= (EditText) findViewById(R.id.exp_date_box_2);
        exp_date_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String curr = s.toString();
                if (curr.length() == 2 && start == 1) {
                    exp_date_box.setText(curr + "/");
                    exp_date_box.setSelection(curr.length() + 1);
                }
                else if (curr.length() == 2 && before == 1) {
                    curr = curr.substring(0, 1);
                    exp_date_box.setText(curr);
                    exp_date_box.setSelection(curr.length());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Displaying Card info FROM FireBase
/*
        dFullName = findViewById(R.id.display_card_name);
        dExp_Date = findViewById(R.id.display_card_exp);
        dCardNum  = findViewById(R.id.display_card_num);
        DocumentReference documentReference = fStore.collection("PaymentMethod_1").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    dFullName.setText(documentSnapshot.getString("Billing_Name_1"));
                    dExp_Date.setText(documentSnapshot.getString("Billing_Exp_Date_1"));
                    dCardNum.setText(documentSnapshot.getString("Billing_Card_Num_1"));
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
*/
        // *** END Collecting User Payment Method 2 ***
        //-----------------------------------------------






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


    // *** Collecting Payment Method 2 to FireBase
    //--------------------------------------------
    private void addDataToFireBase(String fullName_string, String cardNumber_string, String exp_date_string, String ccv_num_string, String zip_code_string) {
        //Send confirmation email / verification link.
        FirebaseUser fuser = fAuth.getCurrentUser();

        Toast.makeText(PaymentMethod_2_Activity.this, "Card Added.", Toast.LENGTH_SHORT).show();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("PaymentMethod_2").document(userID);
        Map<String,Object> user = new HashMap<>();
        user.put("Billing_Name_2",fullName_string);
        user.put("Billing_Card_Num_2",cardNumber_string);
        user.put("Billing_Exp_Date_2",exp_date_string);
        user.put("Billing_CCV_Num_2",ccv_num_string);
        user.put("Billing_Zip_2",zip_code_string);
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
        //Start new activity

    }

    // *** END of Collecting Payment Method 2 to FireBase
    //--------------------------------------------

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

    public void editPaymentPlansPage(View view) {
        Intent intent = new Intent(this, EditPaymentPlansActivity.class);
        startActivity(intent);
    }
}