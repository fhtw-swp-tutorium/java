package com.github.fhtw.swp.tutorium.e2eTest.support;

import com.github.fhtw.swp.tutorium.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class SwpTestToolProxy {

    private static final Logger LOGGER = LogManager.getLogger(SwpTestToolProxy.class);

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

        LOGGER.info("Creating SwpTestToolProxy for {}", pathToSwpTestTool);

        return new SwpTestToolProxy(pathToSwpTestTool);
    }

    public void run(Pattern pattern, URL patternImplementation) {

        final String pathToPatternImplementationWithoutScheme = patternImplementation.toString().replace("file:/", "");

        LOGGER.info("Testing pattern {} against {}", pattern, patternImplementation);

        final Process swpTestToolProcess = runSwpTestTool(pattern, pathToPatternImplementationWithoutScheme);

        waitForIt(swpTestToolProcess);
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
            return new ProcessBuilder("java", "-jar", pathToSwpTestTool, "-pattern", exercise.toString(), patternImplementation).start();
//            return new ProcessBuilder("java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=5005,server=y", "-jar", pathToSwpTestTool, "-pattern", exercise.toString(), patternImplementation).start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
