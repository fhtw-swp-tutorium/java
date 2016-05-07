package com.github.fhtw.swp.tutorium.composite.matcher;

import com.github.fhtw.swp.tutorium.composite.Leaf;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Implementation note:
 * <p>
 * This matcher does not leverage Hamcrest's compositional power because it actually performs two tasks:
 * <ul>
 * <li>iterating over a collection of elements</li>
 * <li>performing a check on each item</li>
 * </ul>
 * <p>
 * This could also be modeled as a composition of {@link org.hamcrest.Matchers#hasItem(Matcher)} and a single Matcher
 * that checks if a given class (leaf) belongs to the component type. However, this way, the provided description
 * would not be a descriptive as if all tasks are handled by a single matcher.
 */
public class LeafsOfComponentExists extends TypeSafeDiagnosingMatcher<Set<Class<?>>> {

    private final Class<?> componentType;

    public LeafsOfComponentExists(Class<?> componentType) {
        this.componentType = componentType;
    }

    @Override
    protected boolean matchesSafely(Set<Class<?>> leafCandidates, Description mismatchDescription) {

        final List<Class<?>> leafsOfComponentType = leafCandidates.stream().filter(this::belongsToComponentType).collect(toList());

        mismatchDescription
                .appendText("none of the given leafs ")
                .appendValueList("(", ",", ")", leafCandidates)
                .appendText(" belong to component type ")
                .appendValue(componentType);

        return leafsOfComponentType.size() > 0;
    }

    private boolean belongsToComponentType(Class<?> leaf) {
        return leaf.isAnnotationPresent(Leaf.class) && leaf.getAnnotation(Leaf.class).value().equals(componentType);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("at least one leaf for component type ").appendValue(componentType);
    }
}
