package com.github.fhtw.swp.tutorium.observer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import com.github.fhtw.swp.tutorium.common.ConfigurationFactory;
import com.github.fhtw.swp.tutorium.common.Matchers;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class ObserverTest {

    private static final String[] REGISTER_METHOD_PREFIXES = new String[] {"register", "attach", "subscribe", "add"};
    private static final String[] UPDATE_METHOD_PREFIXES = new String[] {"update", "notify"};
    private static final String[] UNREGISTER_METHOD_PREFIXES = new String[] {"unregister", "detach", "unsubscribe",
            "remove"};

    private Set<Class<?>> subjects;
    private MethodExtractor methodExtractor;

    @Before
    public void setUp() throws Exception {

        final Configuration configuration = new ConfigurationFactory().create();

        final Reflections reflections = new Reflections(configuration);
        subjects = reflections.getTypesAnnotatedWith(Subject.class);

        methodExtractor = new MethodExtractor();
    }

    @Test
    public void testSubjectsShouldExist() throws Exception {
        Assert.assertThat(subjects, Matchers.exist());
    }

    @Test
    public void testSubjectsShouldHaveRegisterMethod() throws Exception {

        for (Class<?> subject : subjects) {
            Assert.assertThat(subject, com.github.fhtw.swp.tutorium.observer.Matchers.hasMethodThatStartsWith(REGISTER_METHOD_PREFIXES));
        }
    }

    @Test
    public void testSubjectsShouldHaveUpdateMethod() throws Exception {

        for (Class<?> subject : subjects) {
            Assert.assertThat(subject, com.github.fhtw.swp.tutorium.observer.Matchers.hasMethodThatStartsWith(UPDATE_METHOD_PREFIXES));
        }
    }

    @Test
    public void testSubjectsShouldHaveUnregisterMethod() throws Exception {

        for (Class<?> subject : subjects) {
            Assert.assertThat(subject, com.github.fhtw.swp.tutorium.observer.Matchers.hasMethodThatStartsWith(UNREGISTER_METHOD_PREFIXES));
        }
    }

    @Test
    public void testSubjectObserverShouldBeInterface() throws Exception {

        for (Class<?> subject : subjects) {

            final Method registerMethod = methodExtractor.getMethodsWithAnyPrefix(subject, REGISTER_METHOD_PREFIXES)
                    .iterator().next();
            final Class<?> observerClass = registerMethod.getParameterTypes()[0];

            Assert.assertThat(observerClass, Matchers.isInterface());
        }
    }


    @Test
    public void testSubjectsShouldCallObservers() throws Exception {

        for (Class<?> subject : subjects) {

            final Class<?> factoryClass = subject.getAnnotation(Subject.class).factory();
            final boolean useFactory = factoryClass != Subject.None.class;

            final Object subjectInstance = useFactory ? useFactory(factoryClass) : useConstructor(subject);

            final Method registerMethod = methodExtractor.getMethodsWithAnyPrefix(subject, REGISTER_METHOD_PREFIXES)
                    .iterator().next();
            final Method notifyMethod = methodExtractor.getMethodsWithAnyPrefix(subject, UPDATE_METHOD_PREFIXES)
                    .iterator().next();

            final CountingInvocationHandler invocationHandler = new CountingInvocationHandler();
            final Object observer = createObserver(registerMethod, invocationHandler);

            registerMethod.invoke(subjectInstance, observer);
            registerMethod.invoke(subjectInstance, observer);
            notifyMethod.invoke(subjectInstance);

            Assert.assertThat(invocationHandler, com.github.fhtw.swp.tutorium.observer.Matchers.wasInvoked(2));
        }
    }

    @Test
    public void testSubjectsShouldAllowRemovalOfObserver() throws Exception {

        for (Class<?> subject : subjects) {

            final Class<?> factoryClass = subject.getAnnotation(Subject.class).factory();
            final boolean useFactory = factoryClass != Subject.None.class;

            final Object subjectInstance = useFactory ? useFactory(factoryClass) : useConstructor(subject);

            final Method registerMethod = methodExtractor.getMethodsWithAnyPrefix(subject, REGISTER_METHOD_PREFIXES)
                    .iterator().next();
            final Method unregisterMethod = methodExtractor.getMethodsWithAnyPrefix(subject,
                    UNREGISTER_METHOD_PREFIXES).iterator().next();
            final Method updateMethod = methodExtractor.getMethodsWithAnyPrefix(subject, UPDATE_METHOD_PREFIXES)
                    .iterator().next();

            final CountingInvocationHandler invocationHandler = new CountingInvocationHandler();

            final Object observer1 = createObserver(registerMethod, invocationHandler);
            final Object observer2 = createObserver(registerMethod, invocationHandler);

            registerMethod.invoke(subjectInstance, observer1);
            registerMethod.invoke(subjectInstance, observer2);
            unregisterMethod.invoke(subjectInstance, observer1);

            updateMethod.invoke(subjectInstance);

            Assert.assertThat(invocationHandler, com.github.fhtw.swp.tutorium.observer.Matchers.wasInvoked(1));
        }
    }

    private Object createObserver(Method registerMethod, InvocationHandler invocationHandler) throws
            IllegalAccessException, InstantiationException {

        final Class<?> observerClass = registerMethod.getParameterTypes()[0];

        final Class<?> subClass = new ByteBuddy()
                .subclass(Object.class)
                .implement(observerClass)
                .intercept(InvocationHandlerAdapter.of(invocationHandler))
                .make()
                .load(observerClass.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

        return subClass.newInstance();
    }

    private Object useConstructor(Class<?> subjectClass) throws IllegalAccessException, InstantiationException {
        return subjectClass.newInstance();
    }

    @SuppressWarnings("unchecked")
    private Object useFactory(Class<?> factoryClass) throws IllegalAccessException,
            InstantiationException, InvocationTargetException {

        final Object factory = factoryClass.newInstance();

        final Set<Method> factoryMethods = ReflectionUtils.getAllMethods(factoryClass, ReflectionUtils.withModifier(Modifier.PUBLIC),
                ReflectionUtils.withParametersCount(0));

        final Method factoryMethod = factoryMethods.iterator().next();

        return factoryMethod.invoke(factory);
    }

}
