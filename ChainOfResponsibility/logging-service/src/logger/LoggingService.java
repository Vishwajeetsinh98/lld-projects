package logger;

import logger.util.LogLevel;

public class LoggingService {
    private final Logger logger;
    private LogLevel threshold;

    public LoggingService (LogLevel threshold, Logger logger) {
        this.threshold = threshold;
        this.logger = logger;
    }

    public LoggingService(Logger logger) {
        this.threshold = LogLevel.DEBUG;
        this.logger = logger;
    }

    public void setLogLevel(LogLevel level) {
        this.threshold = level;
    }

    public void log(String message) {
        logger.handle(message, threshold);
    }

    public void log(String message, LogLevel level) {
        logger.handle(message, level);
    }
}
