package com.example.carolina_coffee;

import com.example.carolina_coffee.Drink;
import com.example.carolina_coffee.DrinkSize;
import com.example.carolina_coffee.DrinkType;

public class Latte extends Drink {
    public Latte() {
        name = "com.example.carolina_coffee.Latte";
        description = "com.example.carolina_coffee.Espresso mixed with milk";
    }

    public Latte(DrinkType type, DrinkSize size) {
        name = "com.example.carolina_coffee.Latte";
        description = "com.example.carolina_coffee.Espresso mixed with milk";
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
