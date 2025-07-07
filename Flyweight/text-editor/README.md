# LLD Question: Design a Text Editor with Flyweight Pattern

You are asked to design the backend for a Text Editor (like VSCode or Notepad) that efficiently handles displaying a large number of characters on the screen.

## Requirements:
Each character in the editor has:
- The character itself (e.g., 'a', 'b', 'c'...).
- A font family (e.g., Arial, Times New Roman, Courier).
- A font size.
- A color.
- Its position on the screen (X, Y coordinates).

Many characters share the same font, size, and color, but differ in their actual character or position.

## Your design should:
- Minimize memory usage by sharing common data (intrinsic state).
- Keep per-character unique data (extrinsic state) outside the shared object.

## Tasks:
✅ Identify the intrinsic and extrinsic state.  
✅ Design a Character Flyweight class that represents shared state.  
✅ Implement a Flyweight Factory to manage and reuse these shared objects.  
✅ Show how you'd render characters on the screen using this structure.

### Follow-up:
If you finish, be ready to answer:

- What real-world scenarios (other than text editors) are ideal for Flyweight?
- How would you extend this design to support bold, italic, or underline styles?