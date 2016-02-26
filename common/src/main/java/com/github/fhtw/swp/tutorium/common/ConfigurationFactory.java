package com.github.fhtw.swp.tutorium.common;

import org.reflections.Configuration;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

public class ConfigurationFactory {

    public Configuration create() {

        final Configuration configuration = new ConfigurationBuilder()
                .setUrls(SwpTestContext.getUrl())
                .setScanners(
                        new MethodAnnotationsScanner(),
                        new TypeAnnotationsScanner(),
                        new SubTypesScanner()
                );

        return configuration;
    }
}
