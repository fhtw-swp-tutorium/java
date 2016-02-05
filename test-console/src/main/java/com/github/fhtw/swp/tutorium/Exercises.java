package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.observer.ObserverTest;
import com.github.fhtw.swp.tutorium.singleton.SingletonTest;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public enum Exercises {
    UE1(ObserverTest.class, SingletonTest.class),
    UE2(),
    UE3();

    private final Class<?>[] unitTestClasses;

    Exercises(Class<?>... unitTestClasses) {
        this.unitTestClasses = unitTestClasses;
    }

    public Class<?>[] getUnitTestClasses() {
        return unitTestClasses;
    }
}
