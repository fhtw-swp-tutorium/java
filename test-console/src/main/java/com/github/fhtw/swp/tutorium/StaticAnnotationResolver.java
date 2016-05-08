package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.command.InvokeCommand;
import com.github.fhtw.swp.tutorium.command.Invoker;
import com.github.fhtw.swp.tutorium.observer.NotifyObservers;
import com.github.fhtw.swp.tutorium.observer.RegisterObserver;
import com.github.fhtw.swp.tutorium.observer.Subject;
import com.github.fhtw.swp.tutorium.observer.UnregisterObserver;
import com.github.fhtw.swp.tutorium.shared.AnnotationResolver;
import com.github.fhtw.swp.tutorium.singleton.Singleton;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StaticAnnotationResolver implements AnnotationResolver {

    private final Map<String, Class<? extends Annotation>> annotationMap = new HashMap<String, Class<? extends Annotation>>() {{
        put("Singleton", Singleton.class);
        put("Invoker", Invoker.class);
        put("InvokeCommand", InvokeCommand.class);
        put("Subject", Subject.class);
        put("RegisterObserver", RegisterObserver.class);
        put("UnregisterObserver", UnregisterObserver.class);
        put("NotifyObservers", NotifyObservers.class);
    }};

    @Override
    public Class<? extends Annotation> resolve(String name) {
        return Optional.ofNullable(annotationMap.get(name)).orElseThrow(() -> {
            final String errorMessage = String.format("Annotation '%s' is not registered", name);
            return new IllegalArgumentException(errorMessage);
        });
    }
}
