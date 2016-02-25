package com.github.fhtw.swp.tutorium.singleton;

import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonAccessor;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import org.junit.Assert;

import java.util.Set;

import static com.github.fhtw.swp.tutorium.common.error.QuietMatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

@SuppressWarnings("SpellCheckingInspection")
public class SingletonSteps {

    private final SingletonDriver singletonDriver;
    private Set<Class<?>> singletonClasses;

    public SingletonSteps() {
        singletonDriver = new SingletonDriver();
    }

    @Gegebensei("^eine Liste von Singletons$")
    public void eineListeVonSingletons() throws Throwable {
        singletonClasses = singletonDriver.getSingletonClasses();
    }

    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        assertThat(singletonClasses, singletonDriver.getSizeMatcher());
    }

    @Dann("^sollen alle Singletons eine Methode zum Zugriff auf die Instanz haben$")
    public void sollenAlleSingletonsEineMethodeZumZugriffAufDieInstanzHaben() throws Throwable {
        for (Class<?> singletonClass : singletonClasses) {
            assertThat(singletonClass, singletonDriver.getAccessorMatcher());
        }
    }

    @Dann("^sollen alle Singletons einen privaten Konstruktor haben$")
    public void sollenAlleSingletonsEinenPrivatenKonstruktorHaben() throws Throwable {
        for (Class<?> singletonClass : singletonClasses) {
            assertThat(singletonClass, singletonDriver.getContructorMatcher());
        }
    }

    @Dann("^sollen alle Singletons immer dieselbe Instanz zurückgeben$")
    public void sollenAlleSingletonsImmerDieselbeInstanzZurueckgeben() throws Throwable {
        for (Class<?> singletonClass : singletonClasses) {
            final SingletonAccessor singletonAccessor = singletonDriver.getSingletonAccessor(singletonClass);

            Assert.assertThat(singletonAccessor.getInstance(), sameInstance(singletonAccessor.getInstance()));
        }
    }
}
