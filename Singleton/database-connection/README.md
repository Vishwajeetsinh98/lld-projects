# Design a Database Connection Manager using the Singleton Pattern.

You are tasked with designing a DatabaseConnectionManager class for an application that connects to a database. The design should ensure:

## Requirements:
- Only one instance of the `DatabaseConnectionManager` exists across the application. 
- The class provides global access to a `getConnection()` method, which returns a simulated database connection (you can use a dummy Connection object or a simple string for now). 
- The connection is established only once, when the `DatabaseConnectionManager` is first accessed. 
- The implementation is **thread-safe**.

## Constraints & Assumptions:
- You don't need to connect to a real database — simulate with a placeholder connection object or string like "DB Connection Established".
- Focus on applying the Singleton Pattern properly.
- Design should prevent creation of multiple instances even in a multithreaded environment.

## Bonus Discussion Points (Optional):
- How would you extend this to manage a connection pool instead of a single connection?
- How would you protect the Singleton from being broken via reflection or serialization?
- Would you use eager initialization, lazy initialization, or static inner class for this use case? Why?