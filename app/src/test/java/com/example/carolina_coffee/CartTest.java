package com.example.carolina_coffee;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class CartTest {

    @Test
    public void addToCartIsCorrect() throws Exception {
        Cart cart = new Cart();
        Latte latte = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        ArrayList<Latte> test = new ArrayList<>();
        test.add(latte);
        Cart exampleCart = new Cart(test);

        cart.addtoCart(latte);
        assertEquals(cart.getCart(), exampleCart.getCart());
    }

    @Test
    public void removeFromCartIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        ArrayList<Latte> test = new ArrayList<>();
        test.add(latte);
        test.remove(latte);
        Cart exampleCart = new Cart(test);


        Cart cart = new Cart();
        cart.addtoCart(latte);
        cart.removefromCart(latte);

        assertEquals(cart.getCart(), exampleCart.getCart());
    }

    @Test
    public void costOfCartIsCorrect() throws Exception {
        Latte drink1 = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        Latte drink2 = new Latte("Latte", "Espresso mixed with milk", "", 5.85, "");

        Cart cart = new Cart();
        cart.addtoCart(drink1);
        cart.addtoCart(drink2);

        double expected = 10.0;
        cart.calaculateCostofCart();
        double actual = cart.getTotal_cart_price();
        assertEquals(actual, expected,0.001);
    }
}