package com.example.carolina_coffee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Addin {
    private double AddPrice;
    private String AddType;
    private ArrayList<HashMap<String, String>> Flavor;

    public Addin() {

    }

    public Addin(double AddPrice, String AddType) {
        this.AddPrice = AddPrice;
        this.AddType = AddType;
    }

    public double getAddPrice() {
        return AddPrice;
    }

    public void setAddPrice(double addPrice) {
        AddPrice = addPrice;
    }

    public String getAddType() {
        return AddType;
    }

    public void setAddType(String addType) {
        AddType = addType;
    }

    public ArrayList<HashMap<String, String>> getFlavor() {
        return Flavor;
    }

    public void setFlavor(ArrayList<HashMap<String, String>> flavor) {
        Flavor = flavor;
    }
}
