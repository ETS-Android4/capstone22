package com.example.carolina_coffee;

import com.example.carolina_coffee.Drink;
import com.example.carolina_coffee.DrinkSize;
import com.example.carolina_coffee.DrinkType;

abstract public class Nutrition extends Drink {

    String name;
    String nutrition = "";
    DrinkType type;
    DrinkSize size;

    public String getName() {
        return size.toString() + " " + type.toString() + " " + name;
    }

    public String getNutrition() {
        return nutrition;
    }
}
