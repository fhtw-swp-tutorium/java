package com.github.fhtw.swp.tutorium.guice;

import com.google.inject.Injector;
import cucumber.runtime.java.guice.InjectorSource;

public class CucumberInjectorSource implements InjectorSource {

    public static Injector instance;

    @Override
    public Injector getInjector() {
        return instance;
    }
}
