package logger.impl;

import logger.BaseLogger;
import logger.Logger;
import logger.util.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger extends BaseLogger {

    public ConsoleLogger() {
        super(LogLevel.DEBUG);
    }

    public ConsoleLogger(LogLevel threshold) {
        super(threshold);
    }

    public ConsoleLogger(Logger next) {
        super(LogLevel.DEBUG, next);
    }

    public ConsoleLogger(LogLevel threshold, Logger next) {
        super(threshold, next);
    }

    @Override
    public void handle(String message, LogLevel level) {
        // Check if this logger can process the message
        // If it can, process the message and
        // pass the request to the next handler.
        if (threshold.compareTo(level) <= 0) {
            System.out.println("ConsoleLogger");
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("[" + level + "] " + timestamp + ": " + message);
            System.out.println("=========================================");
            super.handle(message, level);
        }
    }
}
