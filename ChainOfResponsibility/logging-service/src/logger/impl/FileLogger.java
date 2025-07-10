package logger.impl;

import logger.BaseLogger;
import logger.Logger;
import logger.util.LogLevel;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends BaseLogger {
    private final String FILE_NAME;

    public FileLogger(String fileName) {
        super(LogLevel.INFO);
        this.FILE_NAME = fileName;
    }

    public FileLogger(String fileName, LogLevel threshold) {
        super(threshold);
        this.FILE_NAME = fileName;
    }

    public FileLogger(String fileName, Logger next) {
        super(LogLevel.INFO, next);
        this.FILE_NAME = fileName;
    }

    public FileLogger(String fileName, LogLevel threshold, Logger next) {
        super(threshold, next);
        this.FILE_NAME = fileName;
    }

    @Override
    public void handle(String message, LogLevel level) {
        if (threshold.compareTo(level) <= 0) {
            System.out.println("FileLogger");
            try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write("[" + level + "] " + timestamp + ": " + message);
                writer.write("\n");
                System.out.println("=========================================");
                super.handle(message, level);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
