package core;

public interface ILogger {
    void info(String message);

    void error(String message);

    void warn(String message);

    void debug(String message);

}
