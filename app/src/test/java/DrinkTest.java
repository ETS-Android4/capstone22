import static org.junit.Assert.*;

import com.example.carolina_coffee.Drink;
import com.example.carolina_coffee.DrinkSize;
import com.example.carolina_coffee.DrinkType;
import com.example.carolina_coffee.Latte;
import com.example.carolina_coffee.Syrup;
import com.example.carolina_coffee.SyrupType;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void additions_isCorrect() throws Exception {
        Drink drink = new Latte(DrinkType.Hot, DrinkSize.Small);
        drink = new Syrup(drink, SyrupType.Classic);
        drink = new Syrup(drink, SyrupType.Caramel);
        List<String> expected = Arrays.asList(new String[]{"Classic Syrup", "Caramel Syrup"});
        List<String> actual = drink.getAdditions();
        assertEquals(expected, actual);
    }

}