package com.github.fhtw.swp.tutorium.guice;

import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import cucumber.runtime.java.guice.ScenarioScoped;

@Singleton
public class SingleTypeContextProvider implements Provider<SingleTypeContext> {

    private SingleTypeContext currentContext;

    @ScenarioScoped
    @Override
    public SingleTypeContext get() {
        return currentContext;
    }

    public void setCurrentContext(SingleTypeContext currentContext) {
        this.currentContext = currentContext;
    }
}
