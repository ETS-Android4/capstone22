package com.example.carolina_coffee;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class AddinTest {

    @Test
    public void addinBlankIsCorrect() throws Exception {
        Addin addin = new Addin();

        double actual = 0;
        double expected = addin.getAddPrice();
        assertEquals(actual, expected,0.001);
    }

    @Test
    public void addinPriceIsCorrect() throws Exception {
        Addin addin = new Addin(0.50, "Syrup");

        double actual = 0.50;
        double expected = addin.getAddPrice();
        assertEquals(actual, expected,0.001);
    }

    @Test
    public void addinSetPriceIsCorrect() throws Exception {
        Addin addin = new Addin(0.50, "Syrup");
        addin.setAddPrice(0.60);

        double actual = 0.60;
        double expected = addin.getAddPrice();
        assertEquals(actual, expected,0.001);
    }

    @Test
    public void addinTypeIsCorrect() throws Exception {
        Addin addin = new Addin(0.50, "Syrup");

        assertEquals("Syrup", addin.getAddType());
    }

    @Test
    public void addinSetTypeIsCorrect() throws Exception {
        Addin addin = new Addin(0.50, "Syrup");
        addin.setAddType("Topping");

        assertEquals("Topping", addin.getAddType());
    }

    @Test
    public void addinSetFlavorIsCorrect() throws Exception {
        Addin addin = new Addin(0.50, "Syrup");
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "B");
        list.add(map);
        addin.setFlavor(list);
        assertEquals(list, addin.getFlavor());
    }
}