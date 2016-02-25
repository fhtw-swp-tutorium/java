package com.github.fhtw.swp.tutorium.command;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.fhtw.swp.tutorium.common.ReflectionUtils.invoke;
import static com.github.fhtw.swp.tutorium.common.ReflectionUtils.newInstance;
import static java.lang.reflect.Modifier.PUBLIC;
import static org.reflections.ReflectionUtils.*;

@SuppressWarnings("unchecked")
public class FactoryAwareClassInstanceFactory implements ClassFactory {

    private final Predicate<Class<?>> shouldUseFactory;
    private final Function<Class<?>, Class<?>> factoryAccessor;

    public FactoryAwareClassInstanceFactory(Predicate<Class<?>> shouldUseFactory, Function<Class<?>, Class<?>> factoryAccessor) {
        this.shouldUseFactory = shouldUseFactory;
        this.factoryAccessor = factoryAccessor;
    }

    @Override
    public Object create(Class<?> typeToConstruct) {
        if (shouldUseFactory.test(typeToConstruct)) {
            final Method factoryMethod = getFactoryMethod(typeToConstruct);
            final Object factory = newInstance(factoryAccessor.apply(typeToConstruct));

            return invoke(factoryMethod, factory);
        } else {
            return newInstance(typeToConstruct);
        }
    }

    private Method getFactoryMethod(Class<?> typeToConstruct) {

        Class<?> factoryClass = factoryAccessor.apply(typeToConstruct);

        return ReflectionUtils
                .getAllMethods(factoryClass, withParametersCount(0), withReturnType(typeToConstruct), withModifier(PUBLIC))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No suitable method found on given factory class for subject"));
    }

}
