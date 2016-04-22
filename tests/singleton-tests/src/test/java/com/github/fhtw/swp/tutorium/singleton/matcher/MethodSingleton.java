package com.github.fhtw.swp.tutorium.singleton.matcher;

public class MethodSingleton {

    private static final MethodSingleton instance = new MethodSingleton();

    public static MethodSingleton getInstance() {
        return instance;
    }

    private MethodSingleton() { }
}
