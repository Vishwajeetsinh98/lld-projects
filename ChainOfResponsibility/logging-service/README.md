# LLD Interview Question: Logging Framework
## Problem Statement:
Design a logging framework for an application that supports multiple levels of logging: DEBUG, INFO, ERROR, and FATAL.  
Each log message should be passed through a chain of log handlers that decide whether to handle the message based on its severity.

## Requirements:
1. The logger should support a chain of handlers like ConsoleLogger, FileLogger, and EmailLogger.
2. Each handler decides whether to process the message based on its own severity threshold.
3. If a handler processes the message, it should still pass the message along the chain.
4. You should be able to dynamically configure the chain (e.g., ConsoleLogger -> FileLogger -> EmailLogger).
5. The client code should only need to call a single method, like logger.log("Some message", LogLevel.ERROR).

### Example Flow:
If a log message with level `ERROR` is sent:
- ConsoleLogger (supports DEBUG and above) processes and passes it on.
- FileLogger (supports INFO and above) processes and passes it on.
- EmailLogger (supports ERROR and above) processes it as well.

### 🔧 Expected Concepts
- Use of abstract handler with a setNext() method.
- Each concrete logger handles and delegates to the next.
- Decoupling sender from the actual handler logic.