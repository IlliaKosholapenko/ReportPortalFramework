package core;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public abstract class BaseReporter {
    private static IReporter reporter;

    public static synchronized <T extends IReporter> IReporter getInstance(Class<T> clazz) {
        if (Objects.isNull(reporter)) {
            try {
                reporter = (IReporter) clazz.getConstructors()[0].newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(String.format("Error while creation instance of %s class:%n", clazz.getName()), ex);
            }
        }
        return reporter;
    }
}
