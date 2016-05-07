package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.observer.factory.MethodPrefixMatcherFactory;
import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import com.github.fhtw.swp.tutorium.shared.TypeContext;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.hamcrest.Matcher;

import javax.inject.Inject;
import java.lang.reflect.Method;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.reflection.OnlyInterfaceParametersMethodMatcher.onlyInterfaceParameters;
import static org.hamcrest.reflection.ParameterCountMethodMatcher.parameterCount;
import static org.junit.Assert.assertThat;

@SuppressWarnings("SpellCheckingInspection")
public class ObserverSteps {

    private final ObserverDriver observerDriver;
    private final TypeContext typeContext;
    private final MethodPrefixMatcherFactory methodPrefixMatcherFactory;

    @Inject
    public ObserverSteps(ObserverDriver observerDriver, TypeContext typeContext, MethodPrefixMatcherFactory methodPrefixMatcherFactory) {
        this.observerDriver = observerDriver;
        this.typeContext = typeContext;
        this.methodPrefixMatcherFactory = methodPrefixMatcherFactory;
    }


    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        assertThat(typeContext.getTypes(), is(not(empty())));
    }

    @Dann("^erwarte ich mir eine Methode, die mit einem dieser Präfixe beginnt: \"([^\"]*)\"$")
    public void erwarteIchMirEineMethodeDieMitEinemDieserPräfixeBeginnt(String delimitedPrefixes) throws Throwable {

        final Matcher<Method> hasGivenPrefixes = methodPrefixMatcherFactory.create(delimitedPrefixes);

        for (Class<?> subjectType : typeContext.getTypes()) {
            final Method subjectMethod = typeContext.getFirstMethodOfType(subjectType);

            assertThat(subjectMethod, hasGivenPrefixes);
        }
    }

    @Und("^jede Methode muss genau \"([^\"]*)\" Parameter haben$")
    public void jedeMethodeMussGenauParameterHaben(Integer parameterCount) throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final Method subjectMethod = typeContext.getFirstMethodOfType(subjectType);

            assertThat(subjectMethod, parameterCount(parameterCount));
        }
    }

    @Und("^jeder Parameter muss ein Interface sein$")
    public void jederParameterMussEinInterfaceSein() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final Method subjectMethod = typeContext.getFirstMethodOfType(subjectType);

            assertThat(subjectMethod, onlyInterfaceParameters());
        }
    }

    @Gegebensei("^eine Instanz des Subjekts$")
    public void eineInstanzDesSubjekts() throws Throwable {
        typeContext.getTypes().forEach(observerDriver::createSubjectProxyInstance);
    }

    @Und("^eine Instanz des Beobachters$")
    public void eineInstanzDesBeobachters() throws Throwable {
        typeContext.getTypes().forEach(observerDriver::createObserverProxyInstance);
    }

    @Wenn("^ich diesen Beobachter hinzufügen$")
    public void ichDiesenBeobachterHinzufuegen() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final SubjectProxy subjectProxy = observerDriver.getSubjectProxyInstance(subjectType);
            final ObserverProxy observerProxy = observerDriver.getObserverProxyInstance(subjectType);

            subjectProxy.register(observerProxy);
        }
    }

    @Und("^ich diesen Beobachter entferne$")
    public void ichDiesenBeobachterEntferne() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final SubjectProxy subjectProxy = observerDriver.getSubjectProxyInstance(subjectType);
            final ObserverProxy observerProxy = observerDriver.getObserverProxyInstance(subjectType);

            subjectProxy.unregister(observerProxy);
        }
    }

    @Und("^die Methode zum Aktualisieren aufrufe$")
    public void dieMethodeZumAktualisierenAufrufe() throws Throwable {
        typeContext.getTypes().stream().map(observerDriver::getSubjectProxyInstance).forEach(SubjectProxy::update);
    }

    @Dann("^soll mindestens eine Methode des Beobachters aufgerufen werden$")
    public void sollMindestensEineMethodeDesBeobachtersAufgerufenWerden() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final ObserverProxy observerProxy = observerDriver.getObserverProxyInstance(subjectType);
            final CountingInvocationHandler invocationHandler = observerProxy.getCountingInvocationHandler();

            assertThat(invocationHandler.getInvocationCount(), is(greaterThan(0)));
        }
    }

    @Dann("^soll keine Methode des Beobachters aufgerufen werden$")
    public void sollKeineMethodeDesBeobachtersAufgerufenWerden() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final ObserverProxy observerProxy = observerDriver.getObserverProxyInstance(subjectType);
            final CountingInvocationHandler invocationHandler = observerProxy.getCountingInvocationHandler();

            assertThat(invocationHandler.getInvocationCount(), is(0));
        }
    }
}
