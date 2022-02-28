package com.example.carolina_coffee;

import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistory {
    private double OrderPrice;
    private String OrderUsername;
    private ArrayList<HashMap<String, String>> Drink;

    public OrderHistory() {

    }

    public OrderHistory(ArrayList<HashMap<String, String>> drink) {
        Drink = drink;
    }

    public ArrayList<HashMap<String, String>> getDrink() {
        return Drink;
    }

    public void setDrink(ArrayList<HashMap<String, String>> drink) {
        Drink = drink;
    }

    public double getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getOrderUsername() {
        return OrderUsername;
    }

    public void setOrderUsername(String orderUsername) {
        OrderUsername = orderUsername;
    }
}
