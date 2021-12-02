package com.example.carolina_coffee;

import java.util.List;

public class Syrup extends AdditionDecorator {
    Drink drink;
    SyrupType syrupType;

    public Syrup(Drink drink, SyrupType syrupType) {
        this.drink = drink;
        this.syrupType = syrupType;
        drink.getAdditions().add(syrupType.toString() + " Syrup");
    }

    public List<String> getAdditions() {
        return drink.getAdditions();
    }

}
