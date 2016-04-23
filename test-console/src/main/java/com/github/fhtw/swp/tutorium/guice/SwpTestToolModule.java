package com.github.fhtw.swp.tutorium.guice;

import com.github.fhtw.swp.tutorium.MutableClassLoader;
import com.github.fhtw.swp.tutorium.ReflectionsSubTypeFinder;
import com.github.fhtw.swp.tutorium.StaticAnnotationResolver;
import com.github.fhtw.swp.tutorium.shared.AnnotationResolver;
import com.github.fhtw.swp.tutorium.shared.TypeContext;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.hamcrest.reflection.SubTypeFinder;
import org.reflections.Configuration;

import java.net.URL;

import static java.lang.ClassLoader.getSystemClassLoader;

public class SwpTestToolModule extends AbstractModule {

    private final URL currentSut;

    public SwpTestToolModule(URL currentSut) {
        this.currentSut = currentSut;
    }

    @Override
    protected void configure() {
        bind(SubTypeFinder.class).to(ReflectionsSubTypeFinder.class);
        bind(AnnotationResolver.class).to(StaticAnnotationResolver.class);
        bind(Configuration.class).toProvider(ConfigurationProvider.class);
        bind(TypeContext.class).in(ScenarioScoped.class);
    }

    @Provides
    @CurrentSut
    public URL getUrl() {
        return currentSut;
    }

    @Provides
    public MutableClassLoader getClassLoader() {
        return new MutableClassLoader(getSystemClassLoader());
    }
}
