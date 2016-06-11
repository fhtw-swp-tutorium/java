package com.github.fhtw.swp.tutorium.e2eTest.support;

import com.github.fhtw.swp.tutorium.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PatternE2ETestConfiguration {

    Pattern patternToTest();
    String jUnitResultFileName();
    String pathToImplementationJar();
    Expectation expectation();
}
