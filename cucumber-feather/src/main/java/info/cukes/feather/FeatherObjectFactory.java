package info.cukes.feather;

import cucumber.api.java.ObjectFactory;
import org.codejargon.feather.Feather;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class FeatherObjectFactory implements ObjectFactory {

    private static Feather feather;
    private final Map<Class<?>, Object> instanceCache;

    public FeatherObjectFactory() {
        instanceCache = new HashMap<>();
    }

    public static void setFeather(Feather feather) {
        FeatherObjectFactory.feather = feather;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        instanceCache.clear();
    }

    @Override
    public void addClass(Class<?> glueClass) {

    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        return (T) instanceCache.computeIfAbsent(glueClass, this::createInstance);
    }

    private Object createInstance(Class<?> c) {
        return feather.instance(c);
    }
}
