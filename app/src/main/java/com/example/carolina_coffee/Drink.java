package com.example.carolina_coffee;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink {
    String Name;
    String Image;
    String Description = "";
    double Price;
    String DrinkID = "";

    List<String> additions = new ArrayList<>();
    String type;
    String size;

    public Drink() {

    }

    public Drink(String description, String drinkID, String image, String name, double price) {
        Name = name;
        Description = description;
        Image = image;
        Price = price;
        DrinkID = drinkID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFullName() {
        return size + " " + type + " " + Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<String> getAdditions() {
        return additions;
    }

    public void setAdditions(List<String> additions) {
        this.additions = additions;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(String drinkID) {
        DrinkID = drinkID;
    }
}