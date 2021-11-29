package com.example.carolina_coffee;

import java.util.List;

public class Espresso extends AdditionDecorator {
    Drink drink;
    EspressoType espressoType;
    int quantity;

    public Espresso(Drink drink, EspressoType espressoType, int quantity) {
        this.drink = drink;
        this.espressoType = espressoType;
        this.quantity = quantity;
        if(this.quantity > 1) {
            drink.getAdditions().add(this.quantity + " " + espressoType.toString() + " Shots");
        }
        else {
            drink.getAdditions().add(this.quantity + " " + espressoType.toString() + " Shot");
        }

    }

    public List<String> getAdditions() {
        return drink.getAdditions();
    }

    public double getCost() {
        return 1.00 + drink.getCost();
    }
}
