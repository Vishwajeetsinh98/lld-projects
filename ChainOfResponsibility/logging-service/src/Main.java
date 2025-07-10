import logger.Logger;
import logger.LoggingService;
import logger.impl.ConsoleLogger;
import logger.impl.FileLogger;
import logger.impl.MailLogger;
import logger.util.LogLevel;

public class Main {
    public static void main(String[] args) {
        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger("test.txt");
        Logger mailLogger = new MailLogger("test@xyz.com");
        fileLogger.setNext(mailLogger);
        consoleLogger.setNext(fileLogger);

        LoggingService logger = new LoggingService(consoleLogger);

        logger.log("This is a DEBUG test");
        logger.log("This is a WARN test", LogLevel.WARN);
        logger.log("This is an ERROR test", LogLevel.ERROR);
        logger.log("This is a FATAL test", LogLevel.FATAL);
        logger.log("This is an INFO test that won't be called", LogLevel.INFO);
    }
}