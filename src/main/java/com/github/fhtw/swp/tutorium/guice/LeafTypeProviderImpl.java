package com.github.fhtw.swp.tutorium.guice;

import com.github.fhtw.swp.tutorium.composite.Leaf;
import com.github.fhtw.swp.tutorium.composite.LeafTypeProvider;
import com.github.fhtw.swp.tutorium.reflection.AnnotatedTypeFinder;
import org.reflections.Configuration;

import javax.inject.Inject;
import java.util.Set;

public class LeafTypeProviderImpl implements LeafTypeProvider {

    private final Configuration configuration;

    @Inject
    public LeafTypeProviderImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Set<Class<?>> getLeafTypes() {
        return new AnnotatedTypeFinder(configuration, Leaf.class).getAnnotatedTypes();
    }
}
