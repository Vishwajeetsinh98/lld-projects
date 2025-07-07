package beverages.decorators.impl;

import beverages.Beverage;
import beverages.decorators.BeverageDecorator;

public class SugarDecorator extends BeverageDecorator {
    public SugarDecorator(Beverage wrappee) {
        super(wrappee, "Sugar", 0.75);
    }
}
