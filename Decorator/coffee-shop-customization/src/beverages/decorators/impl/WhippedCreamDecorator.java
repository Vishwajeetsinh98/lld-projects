package beverages.decorators.impl;

import beverages.Beverage;
import beverages.decorators.BeverageDecorator;

public class WhippedCreamDecorator extends BeverageDecorator {
    public WhippedCreamDecorator(Beverage wrappee) {
        super(wrappee, "Whipped cream", 2.75);
    }
}
