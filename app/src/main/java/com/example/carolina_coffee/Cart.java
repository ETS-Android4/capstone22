package com.example.carolina_coffee;

import java.util.ArrayList;

public class Cart {
    ArrayList<Latte> cart;

    public Cart() {
        cart = new ArrayList<>();
    }
    public ArrayList<Latte> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Latte> cart) {
        this.cart = cart;
    }

    public void addtoCart(Latte latte) {
        cart.add(latte);
    }




}
