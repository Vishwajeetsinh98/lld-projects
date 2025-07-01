package builder;

import meal.Meal;

public class MealBuilder {
    private Meal meal;
    public MealBuilder () {
        reset();
    }

    public void reset () {
        this.meal = new Meal();
    }

    public MealBuilder withMainItem(String mainItem) {
        meal.setMainItem(mainItem);
        return this;
    }

    public MealBuilder withSideItem(String sideItem) {
        meal.setSideItem(sideItem);
        return this;
    }

    public MealBuilder withDrink(String drink) {
        meal.setDrink(drink);
        return this;
    }

    public MealBuilder withDessert(String dessert) {
        meal.setDessert(dessert);
        return this;
    }

    public MealBuilder isKidsMeal() {
        meal.setKidsMeal(true);
        return this;
    }

    public MealBuilder withToy(String toy) {
        meal.setToy(toy);
        return this;
    }

    public Meal getMeal() {
        Meal meal = this.meal;
        reset();
        return meal;
    }
}
