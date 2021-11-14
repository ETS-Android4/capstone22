import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.Test;

public class DrinkTest {

    @Test
    public void latteName_isCorrect() throws Exception {
        Drink drink = new Latte(DrinkType.Hot, DrinkSize.Large);
        String expected = "Large Hot Latte";
        String actual = drink.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void latteDescription_isCorrect() throws Exception {
        Drink drink = new Latte(DrinkType.Hot, DrinkSize.Large);
        String expected = "Espresso mixed with milk";
        String actual = drink.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void latteCost_isCorrect() throws Exception {
        Drink drink = new Latte(DrinkType.Hot, DrinkSize.Small);
        double expected = 2.95;
        double actual = drink.getCost();
        assertEquals(expected, actual, 0.001);
    }

}