package com.github.fhtw.swp.tutorium.common;

import java.util.Optional;

public final class Optionals {

    private Optionals() { }

    public static <T> Optional<T> either(Optional<T> first, Optional<T> second) {
        return first.isPresent() ? first : second;
    }
}
