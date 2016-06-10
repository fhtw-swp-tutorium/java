package com.github.fhtw.swp.tutorium.e2eTest;

import com.github.fhtw.swp.tutorium.Pattern;
import org.junit.runner.Result;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class SwpTestToolProxy {

    private final String pathToSwpTestTool;

    private SwpTestToolProxy(String pathToSwpTestTool) {
        this.pathToSwpTestTool = pathToSwpTestTool;
    }

    public static SwpTestToolProxy createInstance() {
        final String pathToSwpTestTool = Arrays
                .stream(((URLClassLoader) SwpTestToolProxy.class.getClassLoader()).getURLs())
                .filter(url -> url.getFile().contains("SwpTestTool-"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot find SwpTestTool jar on classpath"))
                .toString()
                .replace("file:/", "");

        return new SwpTestToolProxy(pathToSwpTestTool);
    }

    public Result run(Pattern exercise, URL patternImplementation) {

        final String pathToPatternImplementationWithoutScheme = patternImplementation.toString().replace("file:/", "");

        final Process swpTestToolProcess = runSwpTestTool(exercise, pathToPatternImplementationWithoutScheme);

        waitForIt(swpTestToolProcess);

        return new Result();
    }

    private void waitForIt(Process swpTestToolProcess) {
        try {
            swpTestToolProcess.waitFor();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private Process runSwpTestTool(Pattern exercise, String patternImplementation) {
        try {
            return new ProcessBuilder("java", "-jar", pathToSwpTestTool, "-pattern", exercise.toString(), patternImplementation)
                    .inheritIO()
                    .start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
