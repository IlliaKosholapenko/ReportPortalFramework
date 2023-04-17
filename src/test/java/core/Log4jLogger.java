package core;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jLogger {
    static final Logger logger = Logger.getLogger(Log4jLogger.class);

    public static void main(String[] args) {
    }

    public static synchronized void info(Object message) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.setLayout(new PatternLayout("%d %p  %C{1} - %m%n"));
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);
        logger.info(message);
    }

    public static synchronized void error(Object message) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ERROR);
        consoleAppender.setLayout(new PatternLayout("%d %p  %C{1} - %m%n"));
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);
        logger.error(message);
    }

    public static synchronized void warn(Object message) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.WARN);
        consoleAppender.setLayout(new PatternLayout("%d %p  %C{1} - %m%n"));
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);
        logger.warn(message);
    }

    public static synchronized void debug(String message) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.DEBUG);
        consoleAppender.setLayout(new PatternLayout("%d %p  %C{1} - %m%n"));
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);
        logger.debug(message);
    }

}
