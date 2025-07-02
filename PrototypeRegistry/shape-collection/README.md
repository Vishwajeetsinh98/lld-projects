# LLD Question: Shape Registry with Prototype Pattern
You are asked to design part of a Graphic Design Tool where users can quickly create new shapes by copying existing ones from a central registry. To avoid tight coupling between client code and specific shape classes, use the Prototype Design Pattern with a Registry.

## Requirements:
The system should support different types of shapes, all sharing common properties:
- `id`: Unique identifier for each shape (String)
- `color`: Color of the shape (String)

### Specific shape types and their additional properties are:
**Circle**
- `radius`: double

**Rectangle**
- `width`: double
- `height`: double

**Triangle**
- `base`: double
- `height`: double

### There should be a **central** `ShapeRegistry`, which:
- Stores prototype instances of each shape.
- Allows clients to request a new shape by name (e.g., "circle", "rectangle").
- Returns a clone of the prototype, not a direct reference to the prototype itself.

### The client code should
- Never directly use new to create shapes.
- Only interact with the ShapeRegistry and Shape interface.

### Expected Deliverables:
- Design the class structure with appropriate use of inheritance and interfaces.
- Implement the clone() method to enable copying of shapes.
- Demonstrate how new shapes are registered in the ShapeRegistry.
- Show sample client code that retrieves and uses clones of shapes without being aware of concrete classes.
