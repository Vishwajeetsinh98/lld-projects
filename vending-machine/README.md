# LLD Interview Question: Vending Machine (State Pattern)
You're designing a Vending Machine system that sells snacks.

### 📌 Requirements:

- The machine has multiple states:
  - Idle: Waiting for user to insert money
  - HasMoney: Money inserted, waiting for snack selection
  - Dispensing: Dispensing selected snack
  - OutOfStock: No items available

The machine should transition dynamically between states based on user actions:
- Insert money
- Select item
- Dispense item
- Refill machine

Actions should have different behavior depending on the current state.
e.g., Selecting a snack in Idle state should show an error.  
Inserting money in OutOfStock should return the money and show a message.  
The logic for each state should be encapsulated in its own class to allow easy addition/modification of states.

### 🧠 Your Task:
Design the system using the State design pattern.

#### Identify:
- The Context (which holds state)
- The State interface
- Concrete state classes

#### Discuss:
- How each state handles transitions
- How you'd extend the design to add new features like “Maintenance” or “Admin Override”