package com.github.fhtw.swp.tutorium.observer;

import com.github.fhtw.swp.tutorium.common.matcher.MethodNamePrefixMatcher;
import com.google.common.collect.Sets;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.lang.reflect.Method;
import java.util.Set;

import static com.github.fhtw.swp.tutorium.common.matcher.AlwaysFalseMatcher.nothing;
import static com.github.fhtw.swp.tutorium.common.matcher.OnlyInterfaceParametersMethodMatcher.onlyInterfaceParameters;
import static com.github.fhtw.swp.tutorium.common.matcher.ParameterCountMethodMatcher.parameterCount;
import static org.hamcrest.Matchers.allOf;

@SuppressWarnings({"unchecked", "SpellCheckingInspection"})
public enum MethodPrefixes {

    REGISTER(Sets.newHashSet("add", "register", "subscribe", "attach"), singleInterfaceParameter()),
    UNREGISTER(Sets.newHashSet("remove", "unregister", "unsubscribe", "detach"), singleInterfaceParameter()),
    UPDATE(Sets.newHashSet("update", "notify"), noParameters());

    private static Matcher<Method> singleInterfaceParameter() {
        return allOf(parameterCount(1), onlyInterfaceParameters());
    }

    private static Matcher<Method> noParameters() {
        return parameterCount(0);
    }

    private final Set<String> prefixes;
    private final Matcher<Method> additionalMatcher;

    MethodPrefixes(Set<String> prefixes, Matcher<Method> additionalMatcher) {
        this.prefixes = prefixes;
        this.additionalMatcher = additionalMatcher;
    }

    public Method getMethodOn(Class<?> subjectClass) {
        return org.reflections.ReflectionUtils
                .getAllMethods(subjectClass, this::matchesPrefixes)
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public Matcher<Method> getMatcher() {
        return this.getPrefixes()
                .stream()
                .map(MethodNamePrefixMatcher::startsWith)
                .map(this::applyAdditionalMatcher)
                .reduce(nothing(), Matchers::allOf);
    }

    private Matcher<Method> applyAdditionalMatcher(Matcher<Method> existingMethodMatcher) {
        return allOf(existingMethodMatcher, additionalMatcher);
    }

    private boolean matchesPrefixes(Method m) {
        return this.getPrefixes().stream().anyMatch(prefix -> m.getName().startsWith(prefix));
    }

    public Set<String> getPrefixes() {
        return prefixes;
    }
}
