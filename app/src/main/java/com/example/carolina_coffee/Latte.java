package com.example.carolina_coffee;

public class Latte extends Drink{
    public Latte() {
        name = "Latte";
        description = "Espresso mixed with milk";
    }

    public Latte(DrinkType type, DrinkSize size) {
        name = "Latte";
        description = "Espresso mixed with milk";
        this.type = type;
        this.size = size;
    }

    public double getCost() {
        switch(this.size) {
            case Small:
                return 2.95;

            case Large:
                return 4.15;

            case Medium: default:
                return 3.65;
        }
    }
}