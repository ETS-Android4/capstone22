package com.example.carolina_coffee;

import java.util.List;

public class Drizzle extends AdditionDecorator {
    Drink drink;
    DrizzleType drizzleType;

    public Drizzle(Drink drink, DrizzleType drizzleType) {
        this.drink = drink;
        this.drizzleType = drizzleType;
        drink.getAdditions().add(drizzleType.toString() + " Drizzle");
    }

    public List<String> getAdditions() {
        return drink.getAdditions();
    }

    public double getCost() {
        return 0.30 + drink.getCost();
    }
}