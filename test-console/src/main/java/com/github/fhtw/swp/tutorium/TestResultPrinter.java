package com.github.fhtw.swp.tutorium;

import static java.util.stream.Collectors.toList;

import java.io.PrintStream;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class TestResultPrinter {

    private final PrintStream printStream;

    public TestResultPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printResultHeader(Class<?> testClass) {

        final String formatString = "########## Testing %s pattern ##########";
        final String pattern = extractPattern(testClass);

        printStream.println(String.format(formatString, pattern));
        printStream.println();
    }

    public void printResult(Result result) {

        final String formatString = "%d/%d tests passed.";
        final int successCount = result.getRunCount() - result.getFailureCount();

        printStream.println(String.format(formatString, successCount, result.getRunCount()));
        printStream.println();

        final List<String> failuresMessages = result.getFailures().stream().map(Failure::getMessage).collect(toList());

        if (!failuresMessages.isEmpty()) {
            printStream.println("Failures:");
            failuresMessages.forEach(printStream::println);
        }
    }

    public void printResultFooter() {
        printStream.println();
    }

    private String extractPattern(Class<?> unitTestClass) {

        final String unitTestName = unitTestClass.getSimpleName();
        return unitTestName.substring(0, unitTestName.indexOf("Test"));
    }
}
