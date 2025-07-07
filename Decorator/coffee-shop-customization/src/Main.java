import beverages.Beverage;
import beverages.decorators.BeverageDecorator;
import beverages.decorators.impl.MilkDecorator;
import beverages.decorators.impl.SugarDecorator;
import beverages.impl.Coffee;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Coffee();
        beverage = new MilkDecorator(beverage);
        beverage = new SugarDecorator(beverage);
        System.out.println(beverage);
        System.out.println(beverage.getCost());
    }
}