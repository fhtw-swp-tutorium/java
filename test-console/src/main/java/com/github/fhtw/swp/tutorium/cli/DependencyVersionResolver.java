package com.github.fhtw.swp.tutorium.cli;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class DependencyVersionResolver {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String resourceLocationTemplate = "/META-INF/maven/%s/%s/pom.properties";

    public static String getVersion(String groupId, String artifactId) {

        LOGGER.debug("Loading version for dependency {}:{}", groupId, artifactId);

        final String resourceLocation = String.format(resourceLocationTemplate, groupId, artifactId);
        final Properties properties = loadProperties(resourceLocation);

        LOGGER.debug("Loaded properties: {}", properties);

        return properties.getProperty("version");
    }

    private static Properties loadProperties(String resourceLocation) {
        final Properties properties = new Properties();

        try {
            properties.load(DependencyVersionResolver.class.getResourceAsStream(resourceLocation));
        } catch (IOException | NullPointerException e) {
            LOGGER.error("Failed to load properties file", e);
        }

        return properties;
    }
}
