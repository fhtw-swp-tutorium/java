package com.github.fhtw.swp.tutorium.common;

import org.codejargon.feather.Provides;
import org.reflections.Configuration;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import javax.inject.Singleton;

public class ConfigurationFactory {

    @Provides
    @Singleton
    public Configuration create(SwpTestContext context) {

        final Configuration configuration = new ConfigurationBuilder()
                .setUrls(
                        context.getUrl()
                )
                .setScanners(
                        new MethodAnnotationsScanner(),
                        new TypeAnnotationsScanner(),
                        new SubTypesScanner()
                );

        return configuration;
    }
}
