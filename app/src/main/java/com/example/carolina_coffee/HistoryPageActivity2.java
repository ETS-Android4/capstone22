package com.example.carolina_coffee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HistoryPageActivity2 extends AppCompatActivity {

    TextView c_drink, c_price, c_card_used;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button resendCode;
    Button resetPassLocal,changeProfileImage;
    FirebaseUser user;
    StorageReference storageReference;

    RecyclerView.Adapter<OrderHistoryViewHolder> drinkAdapter;
    ArrayList<HashMap<String,String>> addins;
    RecyclerView.Adapter<OrdersViewHolder> orders_adapter;
    RecyclerView.Adapter<CartAddinViewHolder> drink_addin_adapter;

    RecyclerView drink_addin_recyler_menu;
    RecyclerView.LayoutManager drink_addin_layoutManager;

    RecyclerView order_drinks_recycler_menu;
    RecyclerView.LayoutManager order_drinks_layoutManager;

    RecyclerView order_recycler_menu;
    RecyclerView.LayoutManager order_layoutManager;

    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;

    NumberFormat f;

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
        setContentView(R.layout.activity_history_page);

        //Rounding for Currency
        f = NumberFormat.getInstance();
        if (f instanceof DecimalFormat) {
            ((DecimalFormat) f).setDecimalSeparatorAlwaysShown(true);
            ((DecimalFormat) f).setMinimumFractionDigits(2);
        }



        //Display history of drinks
        //------------------------------------------
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        DocumentReference documentReference = fStore.collection("NewOrders").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    //These are Working below! use for reference?
                    //c_drink.setText(documentSnapshot.get("Order_Price").toString());
                    //c_drink.setText(documentSnapshot.get("Drinks").toString());
                    //TODO
                    // Get firebase drink array to display properly
                    // Currently this below is not formatting correctly.
                    // Im not sure how to call sub collection or child of Drinks array map.
                    //c_drink.setText(documentSnapshot.get("Drinks").toString());
                    //c_price.setText("Total Price = $"+documentSnapshot.get("Order_Price").toString());



                    //Load menu
                    order_recycler_menu = (RecyclerView)findViewById(R.id.history_recycler_view);
                    order_layoutManager = new LinearLayoutManager(HistoryPageActivity2.this);
                    order_recycler_menu.setLayoutManager(order_layoutManager);
                    order_recycler_menu.setHasFixedSize(false);

                    loadOrders(documentSnapshot);


                    c_card_used = findViewById(R.id.card_used);
                    c_card_used.setText("");


                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });


        //------------------------------------------





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

    public void settingsPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    String order;

    public void loadOrders(DocumentSnapshot documentSnapshot) {
        orders_adapter = new RecyclerView.Adapter<OrdersViewHolder>() {
            @Override
            public void onBindViewHolder(OrdersViewHolder viewHolder, int i) {
                Map<String, Object> map = documentSnapshot.getData();
                Set<String> set = map.keySet();
                String[] array = set.toArray(new String[set.size()]);
                order = array[i];
                viewHolder.orderName.setText(order);
                Map<String, Object> orderDetails = (Map<String, Object>) map.get(order);
                viewHolder.orderPrice.setText(("Price of Order: $" + f.format(orderDetails.get("orderPrice"))));
                String lastDigits = (String)orderDetails.get("paymentUsed");
                viewHolder.paymentUsed.setText("Payment Used: XXXX-XXXX" + lastDigits.substring(14));

                order_drinks_recycler_menu = viewHolder.cartRecycler;
                order_drinks_layoutManager = new LinearLayoutManager(HistoryPageActivity2.this);
                order_drinks_recycler_menu.setLayoutManager(order_drinks_layoutManager);
                order_drinks_recycler_menu.setHasFixedSize(false);

                loadDrinks(documentSnapshot);
            }
            @Override
            public int getItemCount() {
                return documentSnapshot.getData().size();
            }

            @NonNull
            @Override
            public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_history_item, parent, false);
                return new OrdersViewHolder(view);
            }
        };
        order_recycler_menu.setAdapter(orders_adapter);
    }

    public void loadDrinks(DocumentSnapshot documentSnapshot) {
        Map<String, Object> map = documentSnapshot.getData();
        Map<String, Object> orderDetails = (Map<String, Object>) map.get(order);
        ArrayList<HashMap<String, Object>> drinks = (ArrayList<HashMap<String, Object>>) orderDetails.get("cart");

        drinkAdapter = new RecyclerView.Adapter<OrderHistoryViewHolder>() {
            @Override
            public void onBindViewHolder(OrderHistoryViewHolder viewHolder, int i) {
                Object description = drinks.get(i).get("description");
                Object fullName = drinks.get(i).get("fullName");
                Object name = drinks.get(i).get("name");
                Object price = drinks.get(i).get("price");
                addins = (ArrayList<HashMap<String, String>>) drinks.get(i).get("additions");

                drink_addin_recyler_menu = viewHolder.addins;
                drink_addin_layoutManager = new LinearLayoutManager(HistoryPageActivity2.this);
                drink_addin_recyler_menu.setLayoutManager(drink_addin_layoutManager);
                drink_addin_recyler_menu.setHasFixedSize(false);
                loadDrinkAddins();

                viewHolder.drinkName.setText("" + fullName);
                viewHolder.drinkPrice.setText("$" + price);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(HistoryPageActivity2.this, "Order was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public int getItemCount() {
                return drinks.size();
            }

            @NonNull
            @Override
            public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.order_item, parent, false);
                return new OrderHistoryViewHolder(view);
            }
        };
        order_drinks_recycler_menu.setAdapter(drinkAdapter);
    }

    public void loadDrinkAddins() {
        drink_addin_adapter = new RecyclerView.Adapter<CartAddinViewHolder>() {
            @Override
            public void onBindViewHolder(CartAddinViewHolder viewHolder, int i) {
                viewHolder.cart_addin_txt.setText("" + addins.get(i));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(HistoryPageActivity2.this, "Add-in was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return addins.size();
            }

            @NonNull
            @Override
            public CartAddinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cart_addin, parent, false);
                return new CartAddinViewHolder(view);
            }
        };
        drink_addin_recyler_menu.setAdapter(drink_addin_adapter);
    }

}