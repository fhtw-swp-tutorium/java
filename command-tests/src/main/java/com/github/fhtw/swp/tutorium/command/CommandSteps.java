package com.github.fhtw.swp.tutorium.command;

import com.github.fhtw.swp.tutorium.common.AnnotationResolver;
import com.github.fhtw.swp.tutorium.common.TypeContext;
import com.github.fhtw.swp.tutorium.common.matcher.ImplementationExistsMatcher;
import com.github.fhtw.swp.tutorium.common.matcher.OnlyInterfaceParametersMethodMatcher;
import com.github.fhtw.swp.tutorium.common.matcher.ParameterCountMethodMatcher;
import com.github.fhtw.swp.tutorium.reflection.ClassInstanceFactory;
import com.github.fhtw.swp.tutorium.reflection.impl.AnnotatedClassInstanceFactory;
import com.google.common.collect.Maps;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.junit.Assert;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.reflections.ReflectionUtils.withAnnotation;

public class CommandSteps {

    private final TypeContext typeContext;
    private final ClassInstanceFactory invokerFactory;
    private Map<Class<?>, InvokerProxy> invokerProxyMap = Maps.newHashMap();
    private Map<Class<?>, CommandProxy> commandProxyMap = Maps.newHashMap();

    public CommandSteps(TypeContext typeContext) {
        this.typeContext = typeContext;
        this.invokerFactory = new AnnotatedClassInstanceFactory<>(Invoker.class, Invoker::factory, Invoker.None.class);
    }

    @Gegebensei("^eine Liste von Klassen mit dem Attribut \"([^\"]*)\"$")
    public void eineListeVonKlassenMitDemAttribut(String annotationName) throws Throwable {
        typeContext.initializeWithTypesAnnotatedWith(AnnotationResolver.INSTANCE.resolve(annotationName));
    }

    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        Assert.assertThat(typeContext.getTypes(), is(not(empty())));
    }

    @Wenn("^ich in jeder Klasse nach einer Methode mit dem Attribut \"([^\"]*)\" suche$")
    public void ichInJederKlasseNachEinerMethodeMitDemAttributSuche(String annotationName) throws Throwable {
        typeContext.filterMethods(withAnnotation(AnnotationResolver.INSTANCE.resolve(annotationName)));
    }

    @Dann("^erwarte ich mir jeweils eine Methode$")
    public void erwarteIchMirJeweilsEineMethode() throws Throwable {
        for (Class<?> type : typeContext.getTypes()) {
            final Set<Method> methodsOfType = typeContext.getMethodsOfType(type);
            Assert.assertThat(methodsOfType.size(), is(1));
        }
    }

    @Dann("^muss jede Methode genau einen Parameter haben$")
    public void mussJedeMethodeGenauEinenParameterHaben() throws Throwable {
        for (Class<?> type : typeContext.getTypes()) {

            final Method invokeCommandMethod = typeContext.getFirstMethodOfType(type);

            Assert.assertThat(invokeCommandMethod, new ParameterCountMethodMatcher(1));
        }
    }

    @Und("^muss jeder Parameter ein Interface sein$")
    public void mussJederParameterEinInterfaceSein() throws Throwable {
        for (Class<?> type : typeContext.getTypes()) {
            final Method invokeCommandMethod = typeContext.getFirstMethodOfType(type);

            Assert.assertThat(invokeCommandMethod, new OnlyInterfaceParametersMethodMatcher());
        }
    }

    @Dann("^muss es für jeden Interface Parameter eine Implementierung geben$")
    public void mussEsFuerJedenInterfaceParameterEineImplementierungGeben() throws Throwable {
        for (Class<?> type : typeContext.getTypes()) {
            final Iterator<Method> methodIterator = typeContext.getMethodsOfType(type).iterator();

            if (methodIterator.hasNext()) {
                final Method invokeMethod = methodIterator.next();

                if (invokeMethod.getParameterTypes().length > 0) {
                    final Class<?> firstParameter = invokeMethod.getParameterTypes()[0];

                    Assert.assertThat(firstParameter, new ImplementationExistsMatcher(typeContext.getConfiguration()));
                }
            }
        }
    }

    @Und("^ich eine Instanz des Invokers erzeuge$")
    public void ichEineInstanzDesInvokersErzeuge() throws Throwable {

        for (Class<?> invoker : typeContext.getTypes()) {
            final Object invokerInstance = invokerFactory.create(invoker);

            final Method invokeCommandMethod = typeContext.getFirstMethodOfType(invoker);

            invokerProxyMap.put(invoker, new InvokerProxy(invokerInstance, invokeCommandMethod));
        }
    }

    @Und("^ich eine dynamische Instanz des Kommandos erzeuge$")
    public void ichEineDynamischeInstanzDesKommandosErzeuge() throws Throwable {
        for (Class<?> invoker : typeContext.getTypes()) {
            final Method invokeCommandMethod = typeContext.getFirstMethodOfType(invoker);
            final Class<?> commandType = invokeCommandMethod.getParameterTypes()[0];

            final CommandProxy commandProxy = CommandProxy.create(commandType);

            commandProxyMap.put(invoker, commandProxy);
        }
    }

    @Und("^dieses Kommando an den Invoker übergebe$")
    public void diesesKommandoAnDenInvokerUebergebe() throws Throwable {

        for (Class<?> invoker : invokerProxyMap.keySet()) {
            final InvokerProxy invokerProxy = invokerProxyMap.get(invoker);
            final CommandProxy commandProxy = commandProxyMap.get(invoker);

            invokerProxy.execute(commandProxy);
        }
    }

    @Dann("^soll mindestens eine Methode des Kommandos aufgerufen werden$")
    public void sollMindestensEineMethodeDesKommandosAufgerufenWerden() throws Throwable {
        for (CommandProxy commandProxy : commandProxyMap.values()) {
            Assert.assertThat(commandProxy.getInvocationHandler().getCounter(), is(not(0)));
        }
    }
}
