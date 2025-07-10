package logger.impl;

import logger.BaseLogger;
import logger.Logger;
import logger.util.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MailLogger extends BaseLogger {

    private final String MAIL;

    public MailLogger(String mail) {
        super(LogLevel.ERROR);
        this.MAIL = mail;
    }

    public MailLogger(String mail, LogLevel threshold) {
        super(threshold);
        this.MAIL = mail;
    }

    public MailLogger(String mail, Logger next) {
        super(LogLevel.ERROR, next);
        this.MAIL = mail;
    }

    public MailLogger(String mail, LogLevel threshold, Logger next) {
        super(threshold, next);
        this.MAIL = mail;
    }

    @Override
    public void handle(String message, LogLevel level) {
        if (threshold.compareTo(level) <= 0) {
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("MailLogger");
            System.out.println("[" + level + "] " + timestamp + ": " + message);
            System.out.println("=========================================");
            super.handle(message, level);
        }
    }
}
