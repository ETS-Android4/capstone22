package com.example.carolina_coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OrderMenuPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //------------------------
        // Comes first to get rid of the white default loading screen
        // Eseentially this is wokring to bypass and remove default white screen that pops up when loading app.
        // May change this later. ALSO This is also linking to Themes.xml's under Res/Values/themes.
        setTheme(R.style.Theme_Carolina_Coffee);
        //------------------------
        setContentView(R.layout.activity_order_menu_page);
    }
}