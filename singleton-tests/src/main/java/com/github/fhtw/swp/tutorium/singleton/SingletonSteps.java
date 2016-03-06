package com.github.fhtw.swp.tutorium.singleton;

import com.github.fhtw.swp.tutorium.common.TypeContext;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonAccessor;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@SuppressWarnings("SpellCheckingInspection")
public class SingletonSteps {

    private final SingletonDriver singletonDriver;
    private final TypeContext typeContext;

    public SingletonSteps() {
        singletonDriver = new SingletonDriver();
        typeContext = new TypeContext();
    }

    @Gegebensei("^eine Liste von Singletons$")
    public void eineListeVonSingletons() throws Throwable {
        typeContext.initializeWithTypesAnnotatedWith(Singleton.class);
    }

    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        assertThat(typeContext.getTypes(), is(not(empty())));
    }

    @Dann("^sollen alle Singletons eine Methode zum Zugriff auf die Instanz haben$")
    public void sollenAlleSingletonsEineMethodeZumZugriffAufDieInstanzHaben() throws Throwable {
        for (Class<?> singletonClass : typeContext.getTypes()) {
            assertThat(singletonClass, singletonDriver.getAccessorMatcher());
        }
    }

    @Dann("^sollen alle Singletons einen privaten Konstruktor haben$")
    public void sollenAlleSingletonsEinenPrivatenKonstruktorHaben() throws Throwable {
        for (Class<?> singletonClass : typeContext.getTypes()) {
            assertThat(singletonClass, singletonDriver.getConstructorMatcher());
        }
    }

    @Dann("^sollen alle Singletons immer dieselbe Instanz zurückgeben$")
    public void sollenAlleSingletonsImmerDieselbeInstanzZurueckgeben() throws Throwable {
        for (Class<?> singletonClass : typeContext.getTypes()) {
            final SingletonAccessor singletonAccessor = singletonDriver.getSingletonAccessor(singletonClass);

            assertThat(singletonAccessor.getInstance(), is(notNullValue()));
            assertThat(singletonAccessor.getInstance(), sameInstance(singletonAccessor.getInstance()));
        }
    }
}
