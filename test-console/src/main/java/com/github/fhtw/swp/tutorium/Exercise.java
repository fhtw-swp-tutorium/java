package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.command.CommandTest;
import com.github.fhtw.swp.tutorium.observer.ObserverTest;
import com.github.fhtw.swp.tutorium.singleton.SingletonTest;

public enum Exercise {
    UE1(ObserverTest.class, SingletonTest.class, CommandTest.class),
    UE2(),
    UE3();

    private final Class<?>[] unitTestClasses;

    Exercise(Class<?>... unitTestClasses) {
        this.unitTestClasses = unitTestClasses;
    }

    public Class<?>[] getUnitTestClasses() {
        return unitTestClasses;
    }
}
