package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.common.matcher.OnlyInterfaceParametersMethodMatcher;
import com.github.fhtw.swp.tutorium.common.matcher.ParameterCountMethodMatcher;
import com.github.fhtw.swp.tutorium.invoker.InvokeCommand;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.junit.Assert;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.is;

public class CommandSteps {

    private CommandDriver commandDriver;
    private Set<Class<?>> invokers;
    private Set<Method> annotatedMethods;
    private Map<Class<?>, Object> invokerInstanceMap;

    public CommandSteps() {
        commandDriver = new CommandDriver();
    }

    @Gegebensei("^eine Liste von Klassen mit dem Attribut \"([^\"]*)\"$")
    public void eineListeVonKlassenMitDemAttribut(String annotationName) throws Throwable {
        invokers = commandDriver.getInvokers();
    }

    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        Assert.assertThat(invokers, commandDriver.getSizeMatcher());
    }

    @Wenn("^ich in jeder Klasse nach einer Methode mit dem Attribut \"([^\"]*)\" suche$")
    public void ichInJederKlasseNachEinerMethodeMitDemAttributSuche(String annotationName) throws Throwable {
        annotatedMethods = commandDriver.findMethodsAnnotatedWith(InvokeCommand.class);
    }

    @Dann("^erwarte ich mir jeweils eine Methode$")
    public void erwarteIchMirJeweilsEineMethode() throws Throwable {
        Assert.assertThat(annotatedMethods.size(), is(invokers.size()));
    }

    @Dann("^muss jede Methode genau einen Parameter haben$")
    public void mussJedeMethodeGenauEinenParameterHaben() throws Throwable {
        for (Method annotatedMethod : annotatedMethods) {
            Assert.assertThat(annotatedMethod, new ParameterCountMethodMatcher(1));
        }
    }

    @Und("^muss jeder Parameter ein Interface sein$")
    public void mussJederParameterEinInterfaceSein() throws Throwable {
        for (Method annotatedMethod : annotatedMethods) {
            Assert.assertThat(annotatedMethod, new OnlyInterfaceParametersMethodMatcher());
        }
    }

    @Dann("^muss es für jeden Interface Parameter eine Implementierung geben$")
    public void mussEsFuerJedenInterfaceParameterEineImplementierungGeben() throws Throwable {
        for (Method annotatedMethod : annotatedMethods) {
            if (annotatedMethod.getParameterTypes().length > 0) {

                final Class<?> typeOfFirstParameter = annotatedMethod.getParameterTypes()[0];

                Assert.assertThat(typeOfFirstParameter, commandDriver.getImplementationExistsMatcher());
            }
        }
    }

    @Und("^ich eine Instanz des Invokers erzeuge$")
    public void ichEineInstanzDesInvokersErzeuge() throws Throwable {

        invokerInstanceMap = new HashMap<>();

        for (Class<?> invoker : invokers) {
            final Object invokerInstance = commandDriver.getInvokerFactory().create(invoker);

            invokerInstanceMap.put(invoker, invokerInstance);
        }
    }

    @Und("^ich eine dynamische Instanz des Kommandos erzeuge$")
    public void ichEineDynamischeInstanzDesKommandosErzeuge() throws Throwable {
    }

    @Und("^dieses Kommando an den Invoker übergebe$")
    public void diesesKommandoAnDenInvokerUebergebe() throws Throwable {
    }

    @Dann("^soll mindestens eine Methode des Kommandos aufgerufen werden$")
    public void sollMindestensEineMethodeDesKommandosAufgerufenWerden() throws Throwable {
    }
}
