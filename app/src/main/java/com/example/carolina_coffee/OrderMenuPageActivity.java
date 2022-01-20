package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OrderMenuPageActivity extends AppCompatActivity {

    TextView drink_name, drink_price, drink_description;
    ImageView drink_image;

    String drinkID = "";

    FirebaseDatabase database;
    DatabaseReference drinks;

    Latte drink;

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
        setContentView(R.layout.activity_order_menu_page);


        //Firebase
        database = FirebaseDatabase.getInstance();
        drinks = database.getReference("Category");

        //Init view
        drink_name = (TextView) findViewById(R.id.drinkText);
        drink_description = (TextView) findViewById(R.id.drinkDescription);
        drink_price = (TextView) findViewById(R.id.drinkPrice);
        drink_image = (ImageView) findViewById(R.id.drinkCircle);

        //Get Food Id from Intent
        if (getIntent() != null){
            drinkID = getIntent().getStringExtra("DrinkID");
        }
        if(!drinkID.isEmpty()){
            getDetailDrink(drinkID);
        }else {
            Toast.makeText(this, "Error: drinkId = "+drinkID, Toast.LENGTH_SHORT).show();
        }

        continueButton();

    }

    private void getDetailDrink(String drinkID) {
        drinks.child(drinkID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                drink = snapshot.getValue(Latte.class);


                Picasso.with(getBaseContext()).load(drink.getImage()).fit().into(drink_image);

                drink_name.setText(drink.getName());
                drink_price.setText("" + drink.getPrice());
                drink_description.setText(drink.getDescription());

                Toast.makeText(OrderMenuPageActivity.this, drink_name.getText(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // This is method to change the status bar color from default purple to color of choice.
    private void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }

    private void continueButton() {
        Button btnDisplay = (Button)findViewById(R.id.CountinueButton);
        RadioGroup rgSize = (RadioGroup)findViewById(R.id.SizeRadioGroup);
        RadioGroup rgType = (RadioGroup)findViewById(R.id.TypeRadioGroup);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sizeId = rgSize.getCheckedRadioButtonId();
                RadioButton rbSize = (RadioButton)findViewById(sizeId);

                int typeId = rgType.getCheckedRadioButtonId();
                RadioButton rbType = (RadioButton)findViewById(typeId);


                Latte order = new Latte(drink_name.toString(), drink_description.toString(), drink_image.toString(), drink_price.toString(), drinkID);
                order.setSize(rbSize.toString());
                order.setType(rbType.toString());
                //cart.addtoCart(order);
                Intent intent = new Intent(OrderMenuPageActivity.this, CartPageActivity.class);
                overridePendingTransition(0,0);
                startActivity(intent);
                //Toast.makeText(OrderMenuPageActivity.this, rbSize.getText() + " " + rbType.getText() + " " + drink_name.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void backButton(View view) {
        Intent intent = new Intent(this, MenuPageActivity.class);
        overridePendingTransition(0,0);
        startActivity(intent);
    }


}