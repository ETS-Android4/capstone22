package com.example.carolina_coffee;

public class Latte extends Drink{
    public Latte() {

    }

    public Latte(String description, String drinkID, String image, String name, String price) {
        this.Name = name;
        this.Description = description;
        this.Image = image;
        this.Price = price;
        this.DrinkID = drinkID;
        this.type = DrinkType.Hot;
        this.size = DrinkSize.Medium;
    }

    public Latte(String name, String description, String image, String price, DrinkType type, DrinkSize size) {
        this.Name = name;
        this.Description = description;
        this.Image = image;
        this.Price = price;
        this.type = type;
        this.size = size;
    }
}