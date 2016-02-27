package com.github.fhtw.swp.tutorium.observer;

import com.google.common.collect.Sets;

import java.lang.reflect.Method;
import java.util.Set;

@SuppressWarnings({"unchecked", "SpellCheckingInspection"})
public enum MethodPrefixes {

    REGISTER(Sets.newHashSet("add", "register", "subscribe", "attach")),
    UNREGISTER(Sets.newHashSet("remove", "unregister", "unsubscribe", "detach")),
    UPDATE(Sets.newHashSet("update", "notify"));

    private final Set<String> prefixes;

    MethodPrefixes(Set<String> prefixes) {
        this.prefixes = prefixes;
    }

    public Method getMethodOn(Class<?> subjectClass) {
        return org.reflections.ReflectionUtils
                .getAllMethods(subjectClass, this::matchesPrefixes)
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private boolean matchesPrefixes(Method m) {
        return this.getPrefixes().stream().anyMatch(prefix -> m.getName().startsWith(prefix));
    }

    public Set<String> getPrefixes() {
        return prefixes;
    }
}
