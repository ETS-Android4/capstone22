package com.example.carolina_coffee;

import java.util.List;

public class Topping extends AdditionDecorator {
    Drink drink;
    ToppingType toppingType;

    public Topping(Drink drink, ToppingType toppingType) {
        this.drink = drink;
        this.toppingType = toppingType;
        drink.getAdditions().add(toppingType.toString() + " Topping");
    }

    public List<String> getAdditions() {
        return drink.getAdditions();
    }

}