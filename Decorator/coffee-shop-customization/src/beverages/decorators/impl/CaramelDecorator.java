package beverages.decorators.impl;

import beverages.Beverage;
import beverages.decorators.BeverageDecorator;

public class CaramelDecorator extends BeverageDecorator {
    public CaramelDecorator(Beverage wrappee) {
        super(wrappee, "Caramel", 4.5);
    }
}
