package com.example.carolina_coffee;

import java.util.List;

public class Sweetener extends AdditionDecorator {
    Drink drink;
    SweetenerType sweetenerType;
    int quantity;

    public Sweetener(Drink drink, SweetenerType sweetenerType, int quantity) {
        this.drink = drink;
        this.sweetenerType = sweetenerType;
        this.quantity = quantity;
        drink.getAdditions().add(quantity + " " + sweetenerType.toString());
    }

    public List<String> getAdditions() {
        return drink.getAdditions();
    }

    public double getCost() {
        return 0.50 + drink.getCost();
    }
}