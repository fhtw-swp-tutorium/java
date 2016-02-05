package com.github.fhtw.swp.tutorium.common;

import org.reflections.Configuration;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class ConfigurationFactory {

    public Configuration create() {

        final Configuration configuration = new ConfigurationBuilder()
                .setUrls(JarFileUrlHolder.getUrl())
                .setScanners(
                        new MethodAnnotationsScanner(),
                        new TypeAnnotationsScanner(),
                        new SubTypesScanner()
                );

        return configuration;
    }

}
