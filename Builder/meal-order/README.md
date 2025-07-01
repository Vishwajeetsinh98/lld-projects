# LLD Question — Complex Meal Order System
## Problem Statement:

You are designing a system for a Fast-Food Restaurant where customers can customize their meal orders. A Meal consists of multiple optional items, such as:

- Main Item (Burger, Wrap, Sandwich, etc.)
- Side Item (Fries, Nuggets, Salad, etc.)
- Drink (Soda, Juice, Coffee, etc.)
- Dessert (Ice Cream, Cookie, etc.)
- Toys (optional, for kids’ meals)

The ordering process allows customers to pick and choose which items they want. Some items may be optional, and the order in which they select items is flexible.

The construction of a Meal object is complex, with many optional parts, and the client code should be shielded from this complexity.

**Requirements:**
- Use the Builder Pattern to construct Meal objects in a readable, maintainable way.
- Once the Meal is built, it should be immutable.
- The system should allow building:
    - A Standard Meal 
    - A Kids Meal 
    - A Custom Meal

Provide a fluent, readable API for building meals like:
```
  Meal meal = new MealBuilder()
  .withMainItem("Veg Burger")
  .withSideItem("Fries")
  .withDrink("Coke")
  .withDessert("Ice Cream")
  .build();
```

**Deliverables:**
- Design the Meal class and its fields.
- Design the MealBuilder class.
- Show how client code can build different kinds of meals using the builder.
- (Optional) You may add Director classes for predefined meal types like Standard or Kids Meals.