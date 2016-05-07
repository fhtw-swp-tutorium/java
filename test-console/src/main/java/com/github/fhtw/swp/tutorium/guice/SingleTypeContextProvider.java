package com.github.fhtw.swp.tutorium.guice;

import com.github.fhtw.swp.tutorium.shared.SingleTypeContext;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import cucumber.runtime.java.guice.ScenarioScoped;

@Singleton
public class SingleTypeContextProvider {

    private SingleTypeContext currentContext;

    @Provides
    @ScenarioScoped
    public SingleTypeContext getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(SingleTypeContext currentContext) {
        this.currentContext = currentContext;
    }
}
