package beverages.decorators;

import beverages.Beverage;

public abstract class BeverageDecorator extends Beverage {

    protected Beverage wrappee;
    public BeverageDecorator(Beverage wrappee, String name, double cost) {
        super(name, cost);
        this.wrappee = wrappee;
    }

    @Override
    public String toString() {
        return wrappee + " served with [ADDON] " + name + " costing: $" + cost;
    }

    public double getCost() {
        return wrappee.getCost() + this.cost;
    }
}
