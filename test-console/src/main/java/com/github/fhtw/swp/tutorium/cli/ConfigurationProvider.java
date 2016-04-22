package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.inject.CurrentSut;
import org.codejargon.feather.Provides;
import org.reflections.Configuration;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import javax.inject.Singleton;
import java.net.URL;

public class ConfigurationProvider {

    @Provides
    @Singleton
    public Configuration get(@CurrentSut URL currentSut) {

        final Configuration configuration = new ConfigurationBuilder()
                .setUrls(currentSut)
                .setScanners(
                        new MethodAnnotationsScanner(),
                        new TypeAnnotationsScanner(),
                        new SubTypesScanner()
                );

        return configuration;
    }
}
