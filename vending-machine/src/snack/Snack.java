package snack;

public class Snack {
    private final String name;
    private final double price;

    public Snack(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {return name + " (" + price + ")";}

}
