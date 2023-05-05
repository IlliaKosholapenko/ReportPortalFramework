package core;

import io.qameta.allure.Allure;

public class AllureReporter extends BaseReporter implements IReporter{
    public static IReporter getInstance() {
        return getInstance(AllureReporter.class);
    }

    @Override
    public void addAttachment(String name, String content) {
        Allure.addAttachment(name, content);
    }

    @Override
    public void reportLog(String message) {
        addAttachment("[LOG]", message);
    }
}
