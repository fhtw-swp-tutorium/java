package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.common.AnnotationResolver;
import com.github.fhtw.swp.tutorium.common.TypeContext;
import com.github.fhtw.swp.tutorium.observer.factory.MethodPrefixMatcherFactory;
import com.github.fhtw.swp.tutorium.reflection.CountingInvocationHandler;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.hamcrest.Matcher;

import java.lang.reflect.Method;
import java.util.Set;

import static com.github.fhtw.swp.tutorium.common.matcher.OnlyInterfaceParametersMethodMatcher.onlyInterfaceParameters;
import static com.github.fhtw.swp.tutorium.common.matcher.ParameterCountMethodMatcher.parameterCount;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.reflections.ReflectionUtils.withAnnotation;

@SuppressWarnings("SpellCheckingInspection")
public class ObserverSteps {

    private final ObserverDriver observerDriver;
    private final TypeContext typeContext;
    private final MethodPrefixMatcherFactory methodPrefixMatcherFactory;

    public ObserverSteps() {
        this.typeContext = new TypeContext();
        this.observerDriver = new ObserverDriver();
        methodPrefixMatcherFactory = new MethodPrefixMatcherFactory();
    }

    @Gegebensei("^eine Liste von Klassen mit dem Attribut \"([^\"]*)\"$")
    public void eineListeVonKlassenMitDemAttribut(String annotationName) throws Throwable {
        typeContext.initializeWithTypesAnnotatedWith(AnnotationResolver.INSTANCE.resolve(annotationName));
    }

    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        assertThat(typeContext.getTypes(), is(not(empty())));
    }

    @Wenn("^ich in jeder Klasse nach einer Methode mit dem Attribut \"([^\"]*)\" suche$")
    public void ichInJederKlasseNachEinerMethodeMitDemAttributSuche(String annotationName) throws Throwable {
        typeContext.reduceMethods(withAnnotation(AnnotationResolver.INSTANCE.resolve(annotationName)));
    }

    @Dann("^erwarte ich mir jeweils genau eine Methode$")
    public void erwarteIchMirGenauMethode() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final Set<Method> subjectMethods = typeContext.getMethodsOfType(subjectType);
            assertThat(subjectMethods.size(), is(1));
        }
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
    public void ichDiesenBeobachterHinzufügen() throws Throwable {
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
