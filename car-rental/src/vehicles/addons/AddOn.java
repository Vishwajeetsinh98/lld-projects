package vehicles.addons;

public abstract class AddOn {
    protected double cost;
    protected String description;
    public AddOn(double cost, String description) {
        this.cost = cost;
        this.description = description;
    }
    public double getCost() {return cost;}
    public String getDescription() {return description;}
}
