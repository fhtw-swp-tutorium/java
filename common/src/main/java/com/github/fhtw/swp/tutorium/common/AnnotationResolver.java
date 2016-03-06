package com.github.fhtw.swp.tutorium.common;

import com.github.fhtw.swp.tutorium.command.InvokeCommand;
import com.github.fhtw.swp.tutorium.command.Invoker;
import com.github.fhtw.swp.tutorium.observer.NotifyObservers;
import com.github.fhtw.swp.tutorium.observer.RegisterObserver;
import com.github.fhtw.swp.tutorium.observer.Subject;
import com.github.fhtw.swp.tutorium.observer.UnregisterObserver;
import com.github.fhtw.swp.tutorium.singleton.Singleton;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public enum AnnotationResolver {
    INSTANCE;

    private final Map<String, Class<? extends Annotation>> annotationMap = new HashMap<String, Class<? extends Annotation>>() {{
        put("Singleton", Singleton.class);
        put("Invoker", Invoker.class);
        put("InvokeCommand", InvokeCommand.class);
        put("Subject", Subject.class);
        put("RegisterObserver", RegisterObserver.class);
        put("UnregisterObserver", UnregisterObserver.class);
        put("NotifyObservers", NotifyObservers.class);
    }};

    public Class<? extends Annotation> resolve(String name) {
        return annotationMap.get(name);
    }
}
