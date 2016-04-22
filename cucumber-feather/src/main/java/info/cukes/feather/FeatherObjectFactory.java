package info.cukes.feather;

import cucumber.api.java.ObjectFactory;
import org.codejargon.feather.Feather;

public class FeatherObjectFactory implements ObjectFactory {

    private static Feather instance;

    public static void setInstance(Feather instance) {
        FeatherObjectFactory.instance = instance;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void addClass(Class<?> glueClass) {

    }

    @Override
    public <T> T getInstance(Class<T> glueClass) {
        return instance.instance(glueClass);
    }
}
