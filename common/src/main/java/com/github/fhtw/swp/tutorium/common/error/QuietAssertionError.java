package com.github.fhtw.swp.tutorium.common.error;

import java.io.PrintStream;
import java.io.PrintWriter;

public class QuietAssertionError extends AssertionError {
    @Override
    public void printStackTrace() {

    }

    @Override
    public void printStackTrace(PrintStream s) {

    }

    @Override
    public void printStackTrace(PrintWriter s) {

    }
}

