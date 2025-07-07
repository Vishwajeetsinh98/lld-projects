package beverages.decorators.impl;

import beverages.Beverage;
import beverages.decorators.BeverageDecorator;

public class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage wrappee) {
        super(wrappee, "Milk", 1.25);
    }
}
