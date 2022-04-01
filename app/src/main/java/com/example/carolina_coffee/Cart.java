package com.example.carolina_coffee;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    ArrayList<Latte> cart;
    double total_cart_price;

    public Cart() {
        cart = new ArrayList<>();
    }

    public Cart(ArrayList<Latte> cart) {
        this.cart = cart;
    }
    public ArrayList<Latte> getCart() {
        return cart;
    }

    public void addtoCart(Latte latte) {
        cart.add(latte);
    }

    public void removefromCart(Latte latte) { cart.remove(latte);}

    public void calaculateCostofCart() {
        total_cart_price = 0.00;
        for(Latte drink : cart) {
            total_cart_price += drink.getPrice();
        }
        total_cart_price = Math.round(total_cart_price * 100.0)/100.0;
    }

    public double getTotal_cart_price() {
        return total_cart_price;
    }





}
