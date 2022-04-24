package com.example.carolina_coffee;

import java.util.ArrayList;

public class Order {
    String orderUserID;
    ArrayList<Latte> cart;
    Double orderPrice;
    String paymentUsed;

    public Order(String orderUserID, ArrayList<Latte> cart, Double orderPrice, String paymentUsed) {
        this.orderUserID = orderUserID;
        this.cart = cart;
        this.orderPrice = orderPrice;
        this.paymentUsed = paymentUsed;
    }

    public String getOrderUserID() {
        return orderUserID;
    }

    public void setOrderUserID(String orderUserID) {
        this.orderUserID = orderUserID;
    }

    public ArrayList<Latte> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Latte> cart) {
        this.cart = cart;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPaymentUsed() {
        return paymentUsed;
    }

    public void setPaymentUsed(String paymentUsed) {
        this.paymentUsed = paymentUsed;
    }
}
