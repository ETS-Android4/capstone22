package com.example.carolina_coffee;

abstract public class Drink {
    String name;
    String description = "";
    DrinkType type;
    DrinkSize size;


    public String getName() {
        return size.toString() + " " + type.toString() + " " + name;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getCost();
}
