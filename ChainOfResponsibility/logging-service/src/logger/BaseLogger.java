package logger;

import logger.util.LogLevel;

public abstract class BaseLogger implements Logger {
    private Logger next;
    protected LogLevel threshold;

    public BaseLogger (LogLevel threshold) {
        this.threshold = threshold;
        this.next = null;
    }

    public BaseLogger (LogLevel threshold, Logger next) {
        this.threshold = threshold;
        this.next = next;
    }

    @Override
    public void handle(String message, LogLevel level) {
        if (next != null) {
            next.handle(message, level);
        }
        // If the level is FATAL and there is
        // no next handler, terminate the program
        if (level.compareTo(LogLevel.FATAL) == 0 && next == null) {
            System.out.println("Terminating the program!");
            System.exit(1);
        }
    }

    @Override
    public void setNext(Logger next) {
        this.next = next;
    }
}