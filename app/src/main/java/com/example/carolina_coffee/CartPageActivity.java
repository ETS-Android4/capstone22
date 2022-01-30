package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CartPageActivity extends AppCompatActivity {

    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView cart_addin_recyler_menu;
    RecyclerView.LayoutManager cart_addin_layoutManager;

    RecyclerView.Adapter<CartViewHolder> adapter;
    RecyclerView.Adapter<CartAddinViewHolder> cart_addin_adapter;

    Cart cart = OrderMenuPageActivity.getCart();

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
        setContentView(R.layout.activity_cart_page);



        // Navigation
        //--------------------------------------------------------------------------------------
        //Initialize and assign variables - bottom_navigation = nav bar inside activity_main.xml
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home Selected
        bottomNavigationView.setSelectedItemId(R.id.payPageButton);
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

        //Load menu
        recyler_menu = (RecyclerView)findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(this);
        recyler_menu.setLayoutManager(layoutManager);
        recyler_menu.setHasFixedSize(false);

        loadCart();
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, MenuPageActivity.class);
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void loadCart() {
        adapter = new RecyclerView.Adapter<CartViewHolder>() {
            @Override
            public void onBindViewHolder(CartViewHolder viewHolder, int i) {
                drink = cart.getCart().get(i);
                viewHolder.txtDrinkName.setText(drink.getFullName());

                cart_addin_recyler_menu = viewHolder.addinRecycler;
                cart_addin_layoutManager = new LinearLayoutManager(CartPageActivity.this);
                cart_addin_recyler_menu.setLayoutManager(cart_addin_layoutManager);
                cart_addin_recyler_menu.setHasFixedSize(false);
                loadCartAddins();

                viewHolder.txtDrinkPrice.setText("$" + drink.getPrice());
                viewHolder.removeButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cart.removefromCart(drink);
                                Intent intent = new Intent(CartPageActivity.this, CartPageActivity.class);
                                overridePendingTransition(0, 0);
                                startActivity(intent);
                            }
                        }
                );
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(CartPageActivity.this, "Drink was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public int getItemCount() {
                return cart.getCart().size();
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.review_order_item, parent, false);
                return new CartViewHolder(view);
            }
        };
        recyler_menu.setAdapter(adapter);
    }

    public void loadCartAddins() {
        cart_addin_adapter = new RecyclerView.Adapter<CartAddinViewHolder>() {
            @Override
            public void onBindViewHolder(CartAddinViewHolder viewHolder, int i) {
                viewHolder.cart_addin_txt.setText(drink.getAdditions().get(i));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(CartPageActivity.this, "Add-in was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return drink.getAdditions().size();
            }

            @NonNull
            @Override
            public CartAddinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cart_addin, parent, false);
                return new CartAddinViewHolder(view);
            }
        };
        cart_addin_recyler_menu.setAdapter(cart_addin_adapter);
    }

    public void placeOrder(View view) {
        Button btnDisplay = (Button)findViewById(R.id.placeOrderButton);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartPageActivity.this, "Order was placed!", Toast.LENGTH_SHORT).show();
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

    public void paymentPageButton(View view) {
        Intent intent = new Intent(this, PaymentActivity_1.class);
        startActivity(intent);
    }



}