package com.example.carolina_coffee;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    ArrayList<Latte> cart;
    double total_cart_price;

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

    public void removefromCart(Latte latte) { cart.remove(latte);}

    public void calaculateCostofCart() {
        total_cart_price = 0;
        for(Latte drink : cart) {
            total_cart_price += drink.getPrice();
        }
    }




}
