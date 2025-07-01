package builder;

import meal.Meal;

public class Director {
    private final MealBuilder builder;
    public Director(MealBuilder mb) {
        builder = mb;
    }

    public void makeStandardMeal(String mainItem, String sideItem, String drink, String dessert) {
        builder.reset();
        builder.withMainItem(mainItem).withSideItem(sideItem).withDrink(drink).withDessert(dessert);
    }

    public void makeKidsMeal(String mainItem, String sideItem, String drink, String toy) {
        builder.reset();
        builder.isKidsMeal().withMainItem(mainItem).withSideItem(sideItem).withDrink(drink).withToy(toy);
    }

    public Meal getMeal () {
        return this.builder.getMeal();
    }

}
