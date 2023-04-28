package core;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Log4jLogger implements ILogger {

    public static void main(String[] args) {
    }

    public void info(String message) {
        log.info(message);
    }

    public void error(String message) {
        log.error(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void debug(String message) {
        log.debug(message);
    }

}
