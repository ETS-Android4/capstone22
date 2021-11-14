import static org.junit.Assert.*;

import com.example.carolina_coffee.Drink;
import com.example.carolina_coffee.DrinkSize;
import com.example.carolina_coffee.DrinkType;
import com.example.carolina_coffee.Latte;

import org.junit.Test;

public class DrinkTest {

    @Test
    public void latteName_isCorrect() throws Exception {
        Drink drink = new Latte(DrinkType.Hot, DrinkSize.Large);
        String expected = "Large Hot com.example.carolina_coffee.Latte";
        String actual = drink.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void latteDescription_isCorrect() throws Exception {
        Drink drink = new Latte(DrinkType.Hot, DrinkSize.Large);
        String expected = "com.example.carolina_coffee.Espresso mixed with milk";
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