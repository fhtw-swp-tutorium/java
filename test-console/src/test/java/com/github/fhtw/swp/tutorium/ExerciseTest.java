package com.github.fhtw.swp.tutorium;

import com.github.fhtw.swp.tutorium.command.CommandTest;
import com.github.fhtw.swp.tutorium.observer.ObserverTest;
import com.github.fhtw.swp.tutorium.singleton.SingletonTest;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ExerciseTest {

    @Test
    public void shouldReturnCorrectTestClasses() throws Exception {
        final Exercise sut = Exercise.UE1;

        final List<Class<?>> actual = Arrays.asList(sut.getTestClasses());
        final Class[] expected = {ObserverTest.class, SingletonTest.class, CommandTest.class};

        assertThat(actual, Matchers.containsInAnyOrder(expected));
    }
}