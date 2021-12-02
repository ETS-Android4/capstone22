package com.example.carolina_coffee;

import java.util.List;

public class Milk extends AdditionDecorator{
    Drink drink;
    MilkType milkType;

    public Milk(Drink drink, MilkType milkType) {
        this.drink = drink;
        this.milkType = milkType;
        drink.getAdditions().add(milkType.toString() + " Milk");
    }

    public List<String> getAdditions() {
        return drink.getAdditions();
    }

}
