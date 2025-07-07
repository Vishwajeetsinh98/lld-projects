package beverages;

public abstract class Beverage {
    protected String name;
    protected double cost;

    public Beverage(){}

    public Beverage(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "[BEVERAGE] " + name + " cosing: $" + cost;
    }

    public double getCost() {
        return this.cost;
    }
}