package com.example.carolina_coffee;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink {
    String Name;
    String Image;
    String Description = "";
    String Price;
    String DrinkID = "";

    List<String> additions = new ArrayList<>();
    String type;
    String size;

    public Drink() {

    }

    public Drink(String description, String drinkID, String image, String name, String price) {
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
        return size.toString() + " " + type.toString() + " " + Name;
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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
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