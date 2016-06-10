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
                .stream(classpathURLs())
                .filter(url -> url.getFile().contains("SwpTestTool-"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot find SwpTestTool jar on classpath"))
                .toString()
                .replace("file:/", "");

        LOGGER.info("Creating SwpTestToolProxy for {}", pathToSwpTestTool);

        return new SwpTestToolProxy(pathToSwpTestTool);
    }

    private static URL[] classpathURLs() {
        return ((URLClassLoader) SwpTestToolProxy.class.getClassLoader()).getURLs();
    }

    public void run(Pattern pattern, URL patternImplementation) {

        final String pathToPatternImplementationWithoutScheme = patternImplementation.toString().replace("file:/", "");

        LOGGER.info("Testing pattern {} against {}", pattern, pathToPatternImplementationWithoutScheme);

        final Process swpTestToolProcess = runSwpTestTool(pattern, pathToPatternImplementationWithoutScheme);
        waitForIt(swpTestToolProcess);
    }

    private Process runSwpTestTool(Pattern pattern, String patternImplementation) {
        try {
            return new ProcessBuilder("java", "-jar", pathToSwpTestTool, "-pattern", pattern.toString(), patternImplementation).inheritIO().start();
//            return new ProcessBuilder("java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=5005,server=y", "-jar", pathToSwpTestTool, "-pattern", pattern.toString(), patternImplementation).start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void waitForIt(Process swpTestToolProcess) {
        try {
            swpTestToolProcess.waitFor();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
