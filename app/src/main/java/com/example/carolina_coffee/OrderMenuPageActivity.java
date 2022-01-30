package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderMenuPageActivity extends AppCompatActivity {

    private static Cart cart = new Cart();
    TextView drink_name, drink_price, drink_description;
    ImageView drink_image;

    String drinkID = "";

    FirebaseDatabase database;
    DatabaseReference drinks;

    DatabaseReference addins;
    ArrayList<HashMap<String, String>> flavors;
    FirebaseRecyclerAdapter<Addin, AddinViewHolder> adapter;
    RecyclerView.Adapter<FlavorViewHolder> flavorAdapter;

    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView flavor_recyler_menu;
    RecyclerView.LayoutManager flavorlayoutManager;

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
        drinks = database.getReference("Capstone").child("Category");

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

        // Navigation
        //--------------------------------------------------------------------------------------
        //Initialize and assign variables - bottom_navigation = nav bar inside activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.orderPageButton);
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
        //End of onCreate

    }

    public static Cart getCart() {
        return cart;
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



                Latte order = new Latte(drink_name.getText().toString(), drink_description.getText().toString(), "", drink_price.getText().toString(), drinkID);
                order.setSize(rbSize.getText().toString());
                order.setType(rbType.getText().toString());
                cart.addtoCart(order);
                Intent intent = new Intent(OrderMenuPageActivity.this, CartPageActivity.class);
                overridePendingTransition(0,0);
                startActivity(intent);
                Toast.makeText(OrderMenuPageActivity.this, rbSize.getText() + " " + rbType.getText() + " " +  "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void customizeButton(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customizeView = inflater.inflate(R.layout.customize_popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        final PopupWindow popupWindow = new PopupWindow(customizeView, width, height);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        database = FirebaseDatabase.getInstance();
        addins = database.getReference("Capstone").child("Additions");

        //Load menu
        recyler_menu = (RecyclerView)customizeView.findViewById(R.id.popup_addin_view);
        recyler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyler_menu.setLayoutManager(layoutManager);

        loadAddins();

        Button btnDisplay = (Button)customizeView.findViewById(R.id.customize_close_button);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void loadAddins() {
        FirebaseRecyclerOptions<Addin> options =
                new FirebaseRecyclerOptions.Builder<Addin>().setQuery(addins, Addin.class).build();

        adapter = new FirebaseRecyclerAdapter<Addin, AddinViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull AddinViewHolder holder, int position, @NonNull Addin model) {
                flavors = model.getFlavor();
                holder.txtAddinType.setText(model.getAddType());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(OrderMenuPageActivity.this, "Flavor Type was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });

                flavor_recyler_menu = (RecyclerView)holder.addinFlavors;
                flavorlayoutManager = new LinearLayoutManager(OrderMenuPageActivity.this);
                flavor_recyler_menu.setLayoutManager(flavorlayoutManager);
                flavor_recyler_menu.setHasFixedSize(true);
                loadFlavors();
            }

            @NonNull
            @Override
            public AddinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.addin_category, parent, false);
                return new AddinViewHolder(view);
            }
        };
        adapter.startListening();
        recyler_menu.setAdapter(adapter);
    }

    public void loadFlavors() {
        flavorAdapter = new RecyclerView.Adapter<FlavorViewHolder>() {
            @Override
            public void onBindViewHolder(FlavorViewHolder viewHolder, int i) {
                String flavor = flavors.get(i).get("flavorName");
                viewHolder.flavorCheckbox.setText(flavor);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(OrderMenuPageActivity.this, "Flavor was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public int getItemCount() {
                return flavors.size();
            }

            @NonNull
            @Override
            public FlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.checkbox_item, parent, false);
                return new FlavorViewHolder(view);
            }
        };
        flavor_recyler_menu.setAdapter(flavorAdapter);
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, MenuPageActivity.class);
        overridePendingTransition(0,0);
        startActivity(intent);
    }


}