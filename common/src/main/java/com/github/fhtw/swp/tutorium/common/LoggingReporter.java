package com.github.fhtw.swp.tutorium.common;

import gherkin.formatter.Reporter;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingReporter implements Reporter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void before(Match match, Result result) {
        final Throwable error = result.getError();

        if (error != null) {
            LOGGER.error("", error);
        }
    }

    @Override
    public void result(Result result) {
        final Throwable error = result.getError();

        if (error != null) {
            LOGGER.error("", error);
        }
    }

    @Override
    public void after(Match match, Result result) {
        final Throwable error = result.getError();

        if (error != null) {
            LOGGER.error("", error);
        }
    }

    @Override
    public void match(Match match) {

    }

    @Override
    public void embedding(String mimeType, byte[] data) {

    }

    @Override
    public void write(String text) {

    }
}
