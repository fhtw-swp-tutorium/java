package com.github.fhtw.swp.tutorium.observer;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.junit.Assert;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static com.github.fhtw.swp.tutorium.common.ReflectionUtils.invoke;
import static com.github.fhtw.swp.tutorium.common.error.QuietMatcherAssert.assertThat;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("SpellCheckingInspection")
public class ObserverSteps {

    private final ObserverDriver observerDriver;
    private Set<Class<?>> subjectClasses;
    private Map<Class<?>, Object> subjects;
    private Map<Class<?>, Object> observers;
    private Map<Class<?>, CountingInvocationHandler> invocationHandlers;

    public ObserverSteps() {
        observerDriver = new ObserverDriver();
    }

    @Gegebensei("^eine Liste von Subjekten$")
    public void eineListeVonSubjekten() throws Throwable {

        subjectClasses = observerDriver.getSubjects();
        invocationHandlers = subjectClasses.stream().collect(toMap(identity(), this::newInvocationHandler));
        subjects = subjectClasses.stream().collect(toMap(identity(), observerDriver::getSubjectInstance));
    }

    private CountingInvocationHandler newInvocationHandler(Class<?> subjectClass) {
        return new CountingInvocationHandler();
    }

    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        assertThat(subjectClasses, observerDriver.getSizeMatcher());
    }

    @Dann("^sollten alle Subjekte eine Methode zum Hinzufügen bieten$")
    public void solltenAlleSubjekteEineMethodeZumHinzufügenBieten() throws Throwable {
        for (Class<?> subjectClass : subjectClasses) {
            assertThat(allMethods(subjectClass), hasItem(observerDriver.getRegisterMethodMatcher()));
        }
    }

    @Dann("^sollten alle Subjekte eine Methode zum Entfernen bieten$")
    public void solltenAlleSubjekteEineMethodeZumEntfernenBieten() throws Throwable {
        for (Class<?> subjectClass : subjectClasses) {
            assertThat(allMethods(subjectClass), hasItem(observerDriver.getUnregisterMethodMatcher()));
        }
    }

    @Dann("^sollten alle Subjekte eine Methode zum Aktualisieren bieten$")
    public void solltenAlleSubjekteEineMethodeZumAktualisierenBieten() throws Throwable {
        for (Class<?> subjectClass : subjectClasses) {
            assertThat(allMethods(subjectClass), hasItem(observerDriver.getUpdateMethodMatcher()));
        }
    }

    @SuppressWarnings("unchecked")
    private Set<Method> allMethods(Class<?> subjectClass) {
        return ReflectionUtils.getAllMethods(subjectClass);
    }

    @Gegebensei("^ein Beobachter$")
    public void einBeobachter() throws Throwable {
        observers = subjectClasses.stream().collect(toMap(identity(), this::createObserver));
    }

    private Object createObserver(Class<?> subject) {
        final InvocationHandler invocationHandler = invocationHandlers.get(subject);
        return observerDriver.getObserverInstance(subject, invocationHandler);
    }

    @Wenn("^sich dieser Beobachter registriert$")
    public void sichDieserBeobachterRegistriert() throws Throwable {
        for (Class<?> subjectClass : subjectClasses) {
            final Object observer = observers.get(subjectClass);
            final Object subject = subjects.get(subjectClass);
            final Method registerMethod = MethodPrefixes.REGISTER.getMethodOn(subjectClass);

            invoke(registerMethod, subject, observer);
        }
    }

    @Und("^die Method zum Aktualisieren aufgerufen wird$")
    public void dieMethodZumAktualisierenAufgerufenWird() throws Throwable {
        for (Class<?> subjectClass : subjectClasses) {
            final Method updateMethod = MethodPrefixes.UPDATE.getMethodOn(subjectClass);
            final Object subject = subjects.get(subjectClass);

            invoke(updateMethod, subject);
        }
    }

    @Dann("^soll der Beobachter aufgerufen werden$")
    public void sollDerBeobachterAufgerufenWerden() throws Throwable {
        for (CountingInvocationHandler handler : invocationHandlers.values()) {
            Assert.assertThat(handler.getCounter(), is(1));
        }
    }

    @Und("^sich dieser Beobachter wieder abmeldet$")
    public void sichDieserBeobachterWiederAbmeldet() throws Throwable {
        for (Class<?> subjectClass : subjectClasses) {
            final Object observer = observers.get(subjectClass);
            final Object subject = subjects.get(subjectClass);
            final Method unregisterMethod = MethodPrefixes.UNREGISTER.getMethodOn(subjectClass);

            invoke(unregisterMethod, subject, observer);
        }
    }

    @Dann("^soll der Beobachter nicht aufgerufen worden sein$")
    public void sollDerBeobachterNichtAufgerufenWordenSein() throws Throwable {
        for (CountingInvocationHandler handler : invocationHandlers.values()) {
            Assert.assertThat(handler.getCounter(), is(0));
        }
    }
}
