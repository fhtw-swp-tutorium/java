package com.github.fhtw.swp.tutorium.shared;

import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Wenn;

import javax.inject.Inject;

import static org.reflections.ReflectionUtils.withAnnotation;

public class SharedSteps {

    private final TypeContext typeContext;
    private final AnnotationResolver annotationResolver;

    @Inject
    public SharedSteps(TypeContext typeContext, AnnotationResolver annotationResolver) {
        this.typeContext = typeContext;
        this.annotationResolver = annotationResolver;
    }

    @Gegebensei("^eine Liste von Klassen mit dem Attribut \"([^\"]*)\"$")
    public void eineListeVonKlassenMitDemAttribut(String annotationName) throws Throwable {
        typeContext.initializeWithTypesAnnotatedWith(annotationResolver.resolve(annotationName));
    }

    @Wenn("^ich in jeder Klasse nach einer Methode mit dem Attribut \"([^\"]*)\" suche$")
    public void ichInJederKlasseNachEinerMethodeMitDemAttributSuche(String annotationName) throws Throwable {
        typeContext.reduceMethods(withAnnotation(annotationResolver.resolve(annotationName)));
    }
}
