# LLD Question: Undo/Redo in a Text Editor (Command Pattern)

## 📝 Problem Statement:
Design a text editor that supports basic operations like typing, deleting, and undoing/redoing user actions.

### 📌 Requirements:
#### Users should be able to:
- Type text at any cursor position
- Delete text from a specified position
- Undo the last action
- Redo an undone action

#### The editor should:
- Maintain the correct history of actions for undo/redo
- Encapsulate each user action as an independent command
- Support multiple levels of undo and redo
- You should design your solution using the Command Design Pattern.

### 🎯 Key Expectations:
- Use of a Command interface with execute() and undo() methods
- Separate concrete command classes for operations like InsertTextCommand, DeleteTextCommand, etc.
- A TextDocument class as the receiver
- An Editor or Invoker that maintains:
  - undoStack (executed commands)
  - redoStack (undone commands)

### ✅ Example Usage Scenario:
```
1. Type("Hello") at position 0
2. Type(" world") at position 5
3. Delete(5, 11) → removes " world"
4. Undo → restores " world"
5. Undo → removes " world"
6. Redo → adds " world" back
```

### 🔧 Additional Notes:
- You don’t have to build a full GUI or handle cursor movement logic.
- Focus on core modeling and command encapsulation.
- Bonus: Add support for batch commands (e.g., apply a formatting style to a block of text).