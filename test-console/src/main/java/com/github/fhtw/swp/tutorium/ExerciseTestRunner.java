package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.guice.SingleTypeContextProvider;
import com.github.fhtw.swp.tutorium.reflection.AnnotatedTypeFinder;
import com.github.fhtw.swp.tutorium.shared.PatternDefiningAnnotation;
import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.reflections.Configuration;

import java.util.Set;

public class ExerciseTestRunner {

    private static final Logger LOGGER = LogManager.getLogger(ExerciseTestRunner.class);

    private final SingleTypeContextProvider singleTypeContextProvider;
    private final Configuration reflectionsConfiguration;

    @Inject
    public ExerciseTestRunner(SingleTypeContextProvider singleTypeContextProvider, Configuration reflectionsConfiguration) {
        this.singleTypeContextProvider = singleTypeContextProvider;
        this.reflectionsConfiguration = reflectionsConfiguration;
    }

    public void runTests(Class... tests) {
        for (Class<?> testClass : tests) {

            System.out.printf("\n\nTesting %s pattern:\n", getPatternName(testClass));

            if (testClass.isAnnotationPresent(PatternDefiningAnnotation.class)) {

                final Set<Class<?>> annotatedTypes = new AnnotatedTypeFinder(reflectionsConfiguration, testClass.getAnnotation(PatternDefiningAnnotation.class).value()).getAnnotatedTypes();

                for (Class<?> annotatedType : annotatedTypes) {
                    singleTypeContextProvider.setCurrentContext(new SingleTypeContext(annotatedType));

                    final Result result = JUnitCore.runClasses(testClass);
                    result.getFailures().forEach(f -> LOGGER.error("Failed to run test class {}", testClass, f.getException()));
                }

            } else {

                final Result result = JUnitCore.runClasses(testClass);
                result.getFailures().forEach(f -> LOGGER.error("Failed to run test class {}", testClass, f.getException()));
            }
        }
    }

    private String getPatternName(Class<?> unitTestClass) {
        final String className = unitTestClass.getSimpleName();
        return className.substring(0, className.length() - 4);
    }
}
