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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class CartPageActivity extends AppCompatActivity {

    private static final String SCORE = "Score";
    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView cart_addin_recyler_menu;
    RecyclerView.LayoutManager cart_addin_layoutManager;

    RecyclerView.Adapter<CartViewHolder> adapter;
    RecyclerView.Adapter<CartAddinViewHolder> cart_addin_adapter;

    TextView cart_price;

    Cart cart = OrderMenuPageActivity.getCart();

    Latte drink;


    private static final String TAG = "TAG";

    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

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

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        //Load menu
        recyler_menu = (RecyclerView)findViewById(R.id.reviewOrderRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyler_menu.setLayoutManager(layoutManager);
        recyler_menu.setHasFixedSize(false);

        loadCart();
        cart_price = (TextView)findViewById(R.id.cart_cost_text1);
        cart.calaculateCostofCart();
        cart_price.setText("$" + cart.total_cart_price);

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


    // This is method to change the status bar color from default purple to color of choice.
    private void statusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
    }

    public void paymentPageButton(View view) {
        if(cart.getCart().size() == 0) {
            Toast.makeText(CartPageActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
        }
        else {
            addDataToFireBase();

            // Fire Base Increment for tracking rewards.
            //-----------------------------------------
            addIncrementRewardsToFireBase();
            //-----------------------------------------

            cart.getCart().clear();
            Intent intent = new Intent(CartPageActivity.this, CartPageActivity.class);
            overridePendingTransition(0, 0);
            startActivity(intent);
            Toast.makeText(CartPageActivity.this, "Order was placed!", Toast.LENGTH_SHORT).show();


        }
        //Intent intent = new Intent(this, PaymentActivity_1.class);
        //startActivity(intent);

    }

    private void addIncrementRewardsToFireBase() {

        FirebaseUser fuser = fAuth.getCurrentUser();
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("rewards_increment").document(userID);


        // Needs to read existing data from firebase
        int increment = 0;
        //int increment = documentReference.getFirestore();
        //Call increment from firebase so we can +5 points below. otherwise the increment doesnt work and it only stays +5.

        Map<String,Integer> user = new HashMap<>();
        user.put("Increment", +5 );

        


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

    }

    private void addDataToFireBase() {
        FirebaseUser fuser = fAuth.getCurrentUser();


        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Orders").document(userID);
        Map<String,Object> user = new HashMap<>();
        user.put("Order_User_Name", userID);
        user.put("Drinks", cart.getCart());
        user.put("Order_Price", cart.total_cart_price);
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

    }



}