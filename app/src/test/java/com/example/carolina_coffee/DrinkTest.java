package com.example.carolina_coffee;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class DrinkTest {
    @Test
    public void drinkCaffeineIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");
        latte.setCaffeine("30mg");

        assertEquals("30mg", latte.getCaffeine());
    }

    @Test
    public void drinkProteinIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setProtein("7g");

        assertEquals("7g", latte.getProtein());
    }

    @Test
    public void drinkTotalFatIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setTotalFat("10g");
        assertEquals("10g", latte.getTotalFat());
    }

    @Test
    public void drinkServingSizeIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setServingSize("16 fl oz");
        assertEquals("16 fl oz", latte.getServingSize());
    }

    @Test
    public void drinkSaturatedFatIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setSaturatedFat("4.4g");
        assertEquals("4.4g", latte.getSaturatedFat());
    }

    @Test
    public void drinkCholesterolIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setCholesterol("30mg");
        assertEquals("30mg", latte.getCholesterol());
    }

    @Test
    public void drinkSodiumIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setSodium("104mg");
        assertEquals("104mg", latte.getSodium());
    }

    @Test
    public void drinkTotalCarbsIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setTotalCarbs("11g");
        assertEquals("11g", latte.getTotalCarbs());
    }

    @Test
    public void drinkDietaryFiberIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setDietaryFiber("0g");
        assertEquals("0g", latte.getDietaryFiber());
    }

    @Test
    public void drinkSugarsIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setSugars("9.6g");
        assertEquals("9.6g", latte.getSugars());
    }

    @Test
    public void drinkTransFatIsCorrect() throws Exception {
        Latte latte = new Latte("Latte", "Espresso with milk", "latte.jpg", 2.60, "01");        latte.setProtein("7g");
        latte.setTransFat("0g");
        assertEquals("0g", latte.getTransFat());
    }
}