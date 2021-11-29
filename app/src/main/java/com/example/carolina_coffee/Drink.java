package com.example.carolina_coffee;

import java.util.ArrayList;
import java.util.List;

abstract public class Drink {
    String name;
    String description = "";
    List<String> additions = new ArrayList<>();
    DrinkType type;
    DrinkSize size;

    public String getName() {
        return size.toString() + " " + type.toString() + " " + name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAdditions() {
        return additions;
    }

    public abstract double getCost();
}