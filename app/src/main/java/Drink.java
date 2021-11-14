abstract public class Drink {
    String name;
    String description;
    DrinkType type;
    DrinkSize size;
    double cost;

    public String getDescription() {
        return description;
    }

    public abstract int getCost();
}
