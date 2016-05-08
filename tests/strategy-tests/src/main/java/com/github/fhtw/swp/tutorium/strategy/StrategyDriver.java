package com.github.fhtw.swp.tutorium.strategy;

public class StrategyDriver {

    public Class<?> getDefinedStrategyType(Class<?> concreteStrategyType) {
        return concreteStrategyType.getAnnotation(ConcreteStrategy.class).value();
    }
}
