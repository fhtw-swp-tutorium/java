package jdk.util;

import java.util.Arrays;
import java.util.Optional;

public final class Optionals {

    private Optionals() { }

    public static <T> T any(Optional<T>... optionals) {
        return Arrays
                .stream(optionals)
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElseThrow( () -> new IllegalArgumentException("None of the given optionals did return a value"));
    }
}
