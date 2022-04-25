package com.example.carolina_coffee;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class OrderTest {

    @Test
    public void orderCartIsCorrect() throws Exception {
        Cart cart = new Cart();
        Latte latte = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        cart.addtoCart(latte);

        Order order = new Order("USERID", cart.getCart(), cart.getTotal_cart_price(), "1234-5678-8901");
        assertEquals(cart.getCart(), order.getCart());
    }

    @Test
    public void orderPriceIsCorrect() throws Exception {
        Cart cart = new Cart();
        Latte latte = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        cart.addtoCart(latte);

        Order order = new Order("USERID", cart.getCart(), cart.getTotal_cart_price(), "1234-5678-8901");
        double actual = cart.getTotal_cart_price();
        double expected = order.getOrderPrice();
        assertEquals(actual, expected,0.001);
    }

    @Test
    public void orderIDIsCorrect() throws Exception {
        Cart cart = new Cart();
        Latte latte = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        cart.addtoCart(latte);

        Order order = new Order("USERID", cart.getCart(), cart.getTotal_cart_price(), "1234-5678-8901");
        assertEquals("USERID", order.getOrderUserID());
    }

    @Test
    public void orderPaymentIsCorrect() throws Exception {
        Cart cart = new Cart();
        Latte latte = new Latte("Latte", "Espresso mixed with milk", "", 4.15, "");
        cart.addtoCart(latte);

        Order order = new Order("USERID", cart.getCart(), cart.getTotal_cart_price(), "1234-5678-8901");
        assertEquals("1234-5678-8901", order.getPaymentUsed());
    }
}