package com.example.carolina_coffee;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class CategoryTest {

    @Test
    public void categoryBlankIsCorrect() throws Exception {
        Category category = new Category();

        assertEquals(null, category.getName());
    }

    @Test
    public void categoryNameIsCorrect() throws Exception {
        Category category = new Category("Latte", "image.jpg");

        assertEquals("Latte", category.getName());
    }

    @Test
    public void categorySetNameIsCorrect() throws Exception {
        Category category = new Category("Latte", "image.jpg");
        category.setName("Coffee");

        assertEquals("Coffee", category.getName());
    }

    @Test
    public void categoryImageIsCorrect() throws Exception {
        Category category = new Category("Latte", "image.jpg");

        assertEquals("image.jpg", category.getImage());
    }

    @Test
    public void categorySetImageIsCorrect() throws Exception {
        Category category = new Category("Latte", "image.jpg");
        category.setImage("image.png");

        assertEquals("image.png", category.getImage());
    }
}