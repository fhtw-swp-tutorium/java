package com.github.fhtw.swp.tutorium;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class ExerciseTestRunner {

    private final TestResultPrinter testResultPrinter;

    public ExerciseTestRunner(TestResultPrinter testResultPrinter) {
        this.testResultPrinter = testResultPrinter;
    }

    public void runExerciseTests(Exercise exercise) {
        final Class<?>[] unitTestClasses = exercise.getUnitTestClasses();

        for (Class<?> unitTestClass : unitTestClasses) {

            final Result result = JUnitCore.runClasses(unitTestClass);

            /*
            testResultPrinter.printResultHeader(unitTestClass);
            testResultPrinter.printResult(result);
            testResultPrinter.printResultFooter();
            */
        }
    }
}
