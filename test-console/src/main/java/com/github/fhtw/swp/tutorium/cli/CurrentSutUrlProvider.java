package com.github.fhtw.swp.tutorium.cli;

import com.github.fhtw.swp.tutorium.inject.CurrentSut;
import org.codejargon.feather.Provides;

import java.net.URL;

public class CurrentSutUrlProvider {

    private final URL currentSut;

    public CurrentSutUrlProvider(URL currentSut) {
        this.currentSut = currentSut;
    }

    @Provides
    @CurrentSut
    public URL get() {
        return currentSut;
    }
}
