# LLD Question — Coffee Shop Beverage Customization

Design a system for a coffee shop where beverages can be dynamically customized with add-ons using the Decorator Pattern.

## Requirements:
There are different base beverages:  
✔ Coffee  
✔ Tea  
✔ Hot Chocolate  

Customers can add optional toppings at runtime:  
✔ Milk  
✔ Sugar  
✔ Whipped Cream  
✔ Caramel  

Each beverage and add-on has:  
✔ A description  
✔ A cost  

Toppings can be stacked — e.g., a Coffee with Milk and Sugar.

The final description and cost should reflect all applied decorations.
### Example Output:
```
Order: Coffee, Milk, Sugar  
Total Cost: $3.50
```

```
Order: Tea, Whipped Cream, Caramel  
Total Cost: $4.25
```  
### Hints for Implementation:
- Create a Beverage interface or abstract class with getDescription() and getCost().
- Concrete beverages implement this.
- Add-ons are implemented as Decorators that wrap a Beverage.
- Each decorator modifies the description and cost.

### Bonus Interview Considerations:
- Discuss trade-offs: alternative is inheritance explosion (MilkCoffee, SugarTea, etc.).
- Consider immutability or thread safety if asked.
- Show how Decorator Pattern keeps the design flexible and open for new toppings.