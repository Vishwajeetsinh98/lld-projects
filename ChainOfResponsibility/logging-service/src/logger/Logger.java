package logger;

import logger.util.LogLevel;

public interface Logger {
    public void handle (String message, LogLevel level);
    public void setNext(Logger next);
}
