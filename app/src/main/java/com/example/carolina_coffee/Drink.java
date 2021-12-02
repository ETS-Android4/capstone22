package com.example.carolina_coffee;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink {
    String name;
    String description = "";
    String image;
    double cost;
    List<String> additions = new ArrayList<>();
    DrinkType type;
    DrinkSize size;

    public Drink() {

    }

    public Drink(String name, String description, String image, double cost, DrinkType type, DrinkSize size) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.cost = cost;
        this.type = type;
        this.size = size;
    }
    public String getName() {
        return name;
    }

    public String getFullName() {
        return size.toString() + " " + type.toString() + " " + name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAdditions() {
        return additions;
    }

    public double getCost() {
        return cost;
    }

    public String getImage() {
        return image;
    }
}