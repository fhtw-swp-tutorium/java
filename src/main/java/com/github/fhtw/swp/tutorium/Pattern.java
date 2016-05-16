package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.command.CommandTest;
import com.github.fhtw.swp.tutorium.composite.CompositeTest;
import com.github.fhtw.swp.tutorium.decorator.DecoratorTest;
import com.github.fhtw.swp.tutorium.observer.ObserverTest;
import com.github.fhtw.swp.tutorium.singleton.SingletonTest;
import com.github.fhtw.swp.tutorium.strategy.StrategyTest;

public enum Pattern {

    SINGLETON(SingletonTest.class),
    OBSERVER(ObserverTest.class),
    COMMAND(CommandTest.class),
    COMPOSITE(CompositeTest.class),
    DECORATOR(DecoratorTest.class),
    STRATEGY(StrategyTest.class);

    private final Class<?> unitTestClass;

    Pattern(Class<?> unitTestClass) {
        this.unitTestClass = unitTestClass;
    }

    public Class<?> getTestClass() {
        return unitTestClass;
    }
}
