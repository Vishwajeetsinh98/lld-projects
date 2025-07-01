import builder.Director;
import builder.MealBuilder;
import meal.Meal;

public class Main {
    public static void main(String[] args) {
        MealBuilder builder = new MealBuilder();

//        builder.isKidsMeal();
//        builder.withMainItem("Burger");
//        builder.withSideItem("Fries");
//        builder.withDessert("Brownie");
//        builder.withDrink("Coke");
//        builder.withToy("Transformers");

        builder.withMainItem("Burger")
                .withSideItem("Fries")
                .withDessert("Brownie")
                .withDrink("Coke");

        Meal meal = builder.getMeal();
        System.out.println(meal);

        Director director = new Director(builder);
        director.makeKidsMeal("Sandwich", "Fries", "Sprite", "Captain America");
        System.out.println(director.getMeal());
        director.makeKidsMeal("Sandwich", "Fries", null, "Captain America");
        System.out.println(director.getMeal());
        director.makeStandardMeal("Sandwich", "Fries", "Coke", "cake");
        System.out.println(director.getMeal());
    }
}