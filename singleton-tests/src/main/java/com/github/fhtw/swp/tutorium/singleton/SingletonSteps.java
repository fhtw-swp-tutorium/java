package com.github.fhtw.swp.tutorium.singleton;

import com.github.fhtw.swp.tutorium.common.AnnotationResolver;
import com.github.fhtw.swp.tutorium.common.TypeContext;
import com.github.fhtw.swp.tutorium.singleton.accessor.SingletonProxy;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;

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

    @Gegebensei("^eine Liste von Klassen mit dem Attribut \"([^\"]*)\"$")
    public void eineListeVonKlassenMitDemAttribut(String annotationName) throws Throwable {
        typeContext.initializeWithTypesAnnotatedWith(AnnotationResolver.INSTANCE.resolve(annotationName));
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
            final SingletonProxy singletonProxy = singletonDriver.getSingletonAccessor(singletonClass);
            assertThat(singletonProxy.getInstance(), sameInstance(singletonProxy.getInstance()));
        }
    }

    @Und("^diese darf nicht null sein$")
    public void undDieseDarfNichtNullSein() throws Throwable {
        for (Class<?> singletonClass : typeContext.getTypes()) {
            final SingletonProxy singletonProxy = singletonDriver.getSingletonAccessor(singletonClass);
            assertThat(singletonProxy.getInstance(), is(notNullValue()));
        }
    }
}
