# LLD Question — Document Cloning System
## Problem Statement:

You are designing a Document Management System where users can create different types of documents such as:

- Text Documents
- Spreadsheets
- Presentations

The system provides a "Duplicate" feature that allows users to quickly create a copy of an existing document.


**Requirements:**
- The system should support deep cloning of documents with minimal coupling.
- Users may duplicate documents at runtime without knowing the exact class of the document.
- Some documents may have complex internal structures, such as:
  - Nested elements like images, tables, or charts.
  - Metadata like author, creation date, etc.

**The system should be designed so that:**
- Adding new document types is easy.
- The duplication logic is centralized and consistent.

**Deliverables:**
Design the class structure for:
- A base Document interface or abstract class with a clone() method.
- Concrete classes like TextDocument, Spreadsheet, Presentation that implement cloning.
- Show how the client can duplicate documents without needing to know their exact type.

_You can assume Java's Cloneable interface or design your own clone() method as needed._