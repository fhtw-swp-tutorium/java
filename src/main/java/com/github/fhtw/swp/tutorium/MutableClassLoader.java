package com.github.fhtw.swp.tutorium;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class MutableClassLoader extends ClassLoader {

    private static final Method addUrlMethod;

    private final ClassLoader delegate;

    static {
        try {
            addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addUrlMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public MutableClassLoader(ClassLoader delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public void addUrl(URL url) {
        try {
            addUrlMethod.invoke(delegate, url);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
