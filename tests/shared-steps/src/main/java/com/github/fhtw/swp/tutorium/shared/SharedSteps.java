package com.github.fhtw.swp.tutorium.shared;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import org.hamcrest.reflection.OnlyInterfaceParametersMethodMatcher;
import org.hamcrest.reflection.ParameterCountMethodMatcher;
import org.junit.Assert;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.reflection.AnnotatedFieldMatcher.hasSingleFieldWithAnnotation;
import static org.hamcrest.reflection.AnnotatedMethodMatcher.hasSingleMethodWithAnnotation;
import static org.junit.Assert.assertThat;
import static org.reflections.ReflectionUtils.withAnnotation;

public class SharedSteps {

    private final TypeContext typeContext;
    private final SingleTypeContext singleTypeContext;
    private final AnnotationResolver annotationResolver;

    @Inject
    public SharedSteps(TypeContext typeContext, SingleTypeContext singleTypeContext, AnnotationResolver annotationResolver) {
        this.typeContext = typeContext;
        this.singleTypeContext = singleTypeContext;
        this.annotationResolver = annotationResolver;
    }

    @Deprecated
    @Gegebensei("^eine Liste von Klassen mit dem Attribut \"([^\"]*)\"$")
    public void eineListeVonKlassenMitDemAttribut(String annotationName) throws Throwable {
        typeContext.initializeWithTypesAnnotatedWith(annotationResolver.resolve(annotationName));
    }

    @Deprecated
    @Dann("^darf diese Liste nicht leer sein$")
    public void darfDieseListeNichtLeerSein() throws Throwable {
        Assert.assertThat(typeContext.getTypes(), is(not(empty())));
    }

    @Deprecated
    @Dann("^muss jede Methode genau einen Parameter haben$")
    public void mussJedeMethodeGenauEinenParameterHaben() throws Throwable {
        for (Class<?> type : typeContext.getTypes()) {
            final Method candidate = typeContext.getFirstMethodOfType(type);
            Assert.assertThat(candidate, new ParameterCountMethodMatcher(1));
        }
    }

    @Deprecated
    @Dann("^erwarte ich mir jeweils genau eine Methode$")
    public void erwarteIchMirGenauMethode() throws Throwable {
        for (Class<?> subjectType : typeContext.getTypes()) {
            final Set<Method> subjectMethods = typeContext.getMethodsOfType(subjectType);
            assertThat(subjectMethods.size(), is(1));
        }
    }

    @Deprecated
    @Wenn("^ich in jeder Klasse nach einer Methode mit dem Attribut \"([^\"]*)\" suche$")
    public void ichInJederKlasseNachEinerMethodeMitDemAttributSuche(String annotationName) throws Throwable {
        typeContext.reduceMethods(withAnnotation(annotationResolver.resolve(annotationName)));
    }

    @Und("^jeder Parameter muss ein Interface sein$")
    public void mussJederParameterEinInterfaceSein() throws Throwable {
        for (Class<?> type : typeContext.getTypes()) {
            final Method firstMethodOfType = typeContext.getFirstMethodOfType(type);
            Assert.assertThat(firstMethodOfType, new OnlyInterfaceParametersMethodMatcher());
        }
    }

    /// NEW API ///


    @Gegebensei("^das Attribut \"([^\"]*)\"$")
    public void dasAttribut(String annotationName) throws Throwable {
        final Class<? extends Annotation> annotation = annotationResolver.resolve(annotationName);
        singleTypeContext.setAnnotation(annotation);
    }

    @Dann("^erwarte ich mir genau eine Methode die mit diesem Attribut annotiert ist$")
    public void erwarteIchMirGenauEineMethodeDieMitDiesemAttributAnnotiertIst() throws Throwable {

        final Class<? extends Annotation> annotation = singleTypeContext.getAnnotation();
        final Class<?> typeUnderTest = singleTypeContext.getTypeUnderTest();

        assertThat(typeUnderTest, hasSingleMethodWithAnnotation(annotation));
    }

    @Dann("^erwarte ich mir ein Feld welches mit diesem Attribut annotiert ist$")
    public void erwarteIchMirEinFeldWelchesMitDiesemAttributAnnotiertIst() throws Throwable {

        final Class<? extends Annotation> annotation = singleTypeContext.getAnnotation();
        final Class<?> typeUnderTest = singleTypeContext.getTypeUnderTest();

        assertThat(typeUnderTest, hasSingleFieldWithAnnotation(annotation));
    }

    @Dann("^erwarte ich mir eine Methode mit genau einem Parameter$")
    public void erwarteIchMirEineMethodeMitGenauEinemParameter() throws Throwable {

        final Method methodUnderTest = singleTypeContext.getMethodUnderTest();

        assertThat(methodUnderTest, new ParameterCountMethodMatcher(1));
    }
}
