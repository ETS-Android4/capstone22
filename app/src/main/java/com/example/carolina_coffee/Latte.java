package com.example.carolina_coffee;

public class Latte extends Drink{
    public Latte() {

    }

    public Latte(String name, String description, String image, double price, String drinkID) {
        this.Name = name;
        this.Description = description;
        this.Image = image;
        this.Price = price;
        this.DrinkID = drinkID;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setSize(String size) {
        this.size = size;
    }
}