package com.example.carolina_coffee;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink {
    String Name;
    String Image;
    String Description = "";
    double Price;
    String DrinkID = "";
    int Calories;
    String TotalFat;
    String ServingSize;
    String SaturatedFat;
    String Cholesterol;
    String Sodium;
    String TotalCarbs;
    String DietaryFiber;
    String Sugars;
    String Protein;
    String Caffeine;
    String TransFat;

    List<String> additions = new ArrayList<>();
    String type;
    String size;

    public Drink() {

    }

    public Drink(String description, String drinkID, String image, String name, double price) {
        Name = name;
        Description = description;
        Image = image;
        Price = price;
        DrinkID = drinkID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFullName() {
        return size + " " + type + " " + Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<String> getAdditions() {
        return additions;
    }

    public void setAdditions(List<String> additions) {
        this.additions = additions;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(String drinkID) {
        DrinkID = drinkID;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public String getTotalFat() {
        return TotalFat;
    }

    public String getTransFat() {
        return TransFat;
    }

    public void setTransFat(String transFat) {
        TransFat = transFat;
    }

    public void setTotalFat(String totalFat) {
        TotalFat = totalFat;
    }

    public String getServingSize() {
        return ServingSize;
    }

    public void setServingSize(String servingSize) {
        ServingSize = servingSize;
    }

    public String getSaturatedFat() {
        return SaturatedFat;
    }

    public void setSaturatedFat(String saturatedFat) {
        SaturatedFat = saturatedFat;
    }

    public String getCholesterol() {
        return Cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        Cholesterol = cholesterol;
    }

    public String getSodium() {
        return Sodium;
    }

    public void setSodium(String sodium) {
        Sodium = sodium;
    }

    public String getTotalCarbs() {
        return TotalCarbs;
    }

    public void setTotalCarbs(String totalCarbs) {
        TotalCarbs = totalCarbs;
    }

    public String getDietaryFiber() {
        return DietaryFiber;
    }

    public void setDietaryFiber(String dietaryFiber) {
        DietaryFiber = dietaryFiber;
    }

    public String getSugars() {
        return Sugars;
    }

    public void setSugars(String sugars) {
        Sugars = sugars;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String protein) {
        Protein = protein;
    }

    public String getCaffeine() {
        return Caffeine;
    }

    public void setCaffeine(String caffeine) {
        Caffeine = caffeine;
    }
}