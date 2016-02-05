package com.github.fhtw.swp.tutorium.observer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import org.reflections.ReflectionUtils;

/**
 * @author Thomas Eizinger, Senacor Technologies AG.
 */
public class MethodExtractor {

    @SuppressWarnings("unchecked")
    public Set<Method> getMethodsWithAnyPrefix(Class<?> clazz, String... prefixes) {
        final ArrayList<String> prefixList = Lists.newArrayList(prefixes);

        final List<Predicate<Method>> predicates = Lists.transform(prefixList, MethodNamePrefixPredicate::startsWith);

        return ReflectionUtils.getAllMethods(clazz, Predicates.or(predicates));
    }
}
