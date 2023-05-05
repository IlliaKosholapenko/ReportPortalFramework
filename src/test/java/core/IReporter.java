package core;

public interface IReporter {
    void addAttachment(String name, String content);

    void reportLog(String message);
}
