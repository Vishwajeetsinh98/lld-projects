## LLD Interview Question: Factory Method
### Question:
Design a Notification System that supports sending different types of notifications, such as Email, SMS, and Push Notification. The system should be designed so that it's easy to add new notification types in the future with minimal code changes.

**Requirements:**

- You need to design the class structure for this system.
- The system should not expose the client to the concrete implementation classes.
- New notification types should be easily pluggable without modifying existing code.
- Demonstrate how the Factory Method fits into this design.

**Bonus:**
If time permits, discuss how this design helps in following the Open/Closed Principle (OCP).

### What I'm Expecting You to Do:
- Identify where Factory Method applies.
- Design interfaces/abstract classes appropriately.
- Show how clients interact only with abstract types, and the factory handles object creation.
- Discuss extensibility (how new types like "WhatsApp Notification" can be added later).