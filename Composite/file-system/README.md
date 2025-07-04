# LLD Question — Composite Pattern: File System Design

## Design a simplified File System structure using the Composite Pattern.

### Requirements:
- You need to support **Files** and **Folders**.
- Both Files and Folders should implement a common interface so that:
- A `File` has a name and size.
- A `Folder` can contain multiple Files or Folders (i.e., nested structure).
- The system should allow calculating the total size of any folder, which includes the size of all its contents recursively.
- **Provide methods to:**
  - Print the structure (e.g., tree view with indentation).
  - Add items to folders.

### Clarifications You Can Ask:
- Should folder size be just the sum of its contents? (Answer: Yes)
- Should files/folders be mutable? (Answer: At least for adding/removing items in folders)

### Hints for Implementation:
- Define a common interface like `FileSystemItem` with methods:
```
String getName();
int getSize();
void printStructure(String indent);  
```
`File` implements this with a simple size and name.  
`Folder` implements this with a list of `FileSystemItems` and recursive logic for size and printing.